package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.functionalinterface.FuelSpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.ConclusiveRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.FuelSpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.GroupRowFilterDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.IntermediateRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.exception.FuelSearchersReadingException;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain.FilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.AbstractConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.AbstractIntermediateRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Component
public final class SimpleTableFuelSearchersReader extends AbstractTableFuelSearchersReader<SimpleTableFuelSearcher> {
    private static final String TAG_NAME_SEARCHERS = "simple-fuel-tables";
    private static final String TAG_NAME_FUEL_HEADER = "fuel-header";
    private static final String TAG_NAME_GROUP_FILTER = "group-filter";

    private final FuelDocument fuelDocument;
    private final FuelSpecificationPropertyExtractorDictionary propertyExtractorDictionary;
    private final GroupRowFilterDictionary groupRowFilterDictionary;
    private final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;

    public SimpleTableFuelSearchersReader(final FuelDocument fuelDocument,
                                          final FuelSpecificationPropertyExtractorDictionary propertyExtractorDictionary,
                                          final GroupRowFilterDictionary groupRowFilterDictionary,
                                          final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary,
                                          final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary) {
        super(TAG_NAME_SEARCHERS);
        this.fuelDocument = fuelDocument;
        this.propertyExtractorDictionary = propertyExtractorDictionary;
        this.groupRowFilterDictionary = groupRowFilterDictionary;
        this.intermediateRowFilterFactoryDictionary = intermediateRowFilterFactoryDictionary;
        this.conclusiveRowFilterFactoryDictionary = conclusiveRowFilterFactoryDictionary;
    }

    @Override
    protected SimpleTableFuelSearcher readSearcher(final CloseableXMLStreamReader streamReader) {
        streamReader.nextStartTag();   //to <name>
        return SimpleTableFuelSearcher.builder()
                .fuelTable(this.readFuelTable(streamReader))
                .fuelHeaders(readFuelHeaders(streamReader))
                .filterChain(this.readFilterChain(streamReader))
                .fuelHeaderCellValueExtractor(this.readFuelHeaderCellValueExtractor(streamReader))
                .build();
    }

    //streamReader points to <name>
    private FuelTable readFuelTable(final CloseableXMLStreamReader streamReader) {
        final String tableName = readFuelTableName(streamReader);
        return this.findTableByName(tableName);
    }

    private static String readFuelTableName(final CloseableXMLStreamReader streamReader) {
        final String tableName = streamReader.findTagText();
        streamReader.nextStartTag();   //to <fuel-headers>
        return tableName;
    }

    private FuelTable findTableByName(final String fuelTableName) {
        return this.fuelDocument.getTables()
                .stream()
                .filter(table -> Objects.equals(table.getName(), fuelTableName))
                .findFirst()
                .orElseThrow(
                        () -> new FuelTableNotExistException(
                                "Table '%s' doesn't exist".formatted(fuelTableName)
                        )
                );
    }

    //stream reader points to <fuel-headers>
    //after this method streamReader will point to <filter-chain>
    private static List<String> readFuelHeaders(final CloseableXMLStreamReader streamReader) {
        final List<String> fuelHeaders = new ArrayList<>();
        streamReader.nextStartTag();   //to <fuel-header>
        while (streamReader.isStartTag(TAG_NAME_FUEL_HEADER)) {
            final String currentFuelHeader = streamReader.findTagText();
            fuelHeaders.add(currentFuelHeader);
            streamReader.nextStartTag();
        }
        return fuelHeaders;
    }

    //streamReader points to <filter-chain>
    private RowFilterChain readFilterChain(final CloseableXMLStreamReader streamReader) {
        final FilterChainBuilder builder = RowFilterChain.builder();
        this.readGroupFilters(streamReader).forEach(builder::intermediateFilter);
        this.readIntermediateFilters(streamReader).forEach(builder::intermediateFilter);
        builder.conclusiveFilter(this.readConclusiveFilter(streamReader));
        return builder.build();
    }

    //streamReader points to <group-filter>
    private List<AbstractGroupRowFilter> readGroupFilters(final CloseableXMLStreamReader streamReader) {
        final List<AbstractGroupRowFilter> filters = new ArrayList<>();
        while (streamReader.isStartTag(TAG_NAME_GROUP_FILTER)) {
            final AbstractGroupRowFilter currentFilter = this.readGroupRowFilter(streamReader);
            filters.add(currentFilter);
        }
        return filters;
    }

    private List<AbstractIntermediateRowFilter> readIntermediateFilters(final CloseableXMLStreamReader streamReader) {
        final List<AbstractIntermediateRowFilter> filters = new ArrayList<>();
        while (streamReader.isStartTag("filter")) {
            final AbstractIntermediateRowFilter currentFilter = this.readIntermediateRowFilter(streamReader);
            filters.add(currentFilter);
        }
        return filters;
    }

    private AbstractConclusiveRowFilter readConclusiveFilter(final CloseableXMLStreamReader streamReader) {
        final String filteringProperty = readFilteringProperty(streamReader);
        final int cellIndex = readPropertyCellIndex(streamReader);
        return this.conclusiveRowFilterFactoryDictionary.find(filteringProperty)
                //TODO: throw exception
                .orElseThrow()
                .apply(cellIndex);
    }

    private AbstractGroupRowFilter readGroupRowFilter(final CloseableXMLStreamReader streamReader) {
        final String filteringProperty = readFilteringProperty(streamReader);
        return this.groupRowFilterDictionary.find(filteringProperty)
                //TODO: throw exception
                .orElseThrow();
    }

    private AbstractIntermediateRowFilter readIntermediateRowFilter(final CloseableXMLStreamReader streamReader) {
        final String filteringProperty = readFilteringProperty(streamReader);
        final int cellIndex = readPropertyCellIndex(streamReader);
        return this.intermediateRowFilterFactoryDictionary.find(filteringProperty)
                //TODO: throw exception
                .orElseThrow()
                .apply(cellIndex);
    }

    //streamReader points to <group-filter>
    private static String readFilteringProperty(final CloseableXMLStreamReader streamReader) {
        streamReader.nextStartTag();   //to <filter-by>
        final String currentProperty = streamReader.findTagText();
        streamReader.nextStartTag();
        return currentProperty;
    }

    private static int readPropertyCellIndex(final CloseableXMLStreamReader streamReader) {
        final String cellIndex = streamReader.findTagText();
        final int a = parseInt(cellIndex);
        streamReader.nextStartTag();
        return a;
    }

    private FuelSpecificationPropertyExtractor readFuelHeaderCellValueExtractor(
            final CloseableXMLStreamReader streamReader) {
        final String fuelHeaderCellProperty = readFuelHeaderCellProperty(streamReader);
        return this.propertyExtractorDictionary.find(fuelHeaderCellProperty)
                .orElseThrow(
                        () -> new FuelSearchersReadingException(
                                "There is no property extractor for property '%s'".formatted(fuelHeaderCellProperty)
                        )
                );
    }

    //stream reader points to <fuel-header-cell-property>
    private static String readFuelHeaderCellProperty(final CloseableXMLStreamReader streamReader) {
        final String fuelHeaderCellProperty = streamReader.findTagText();
        streamReader.nextStartTag();   //to <filter-chain>
        return fuelHeaderCellProperty;
    }

}
