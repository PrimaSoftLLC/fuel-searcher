package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.IntermediateRowFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.IntermediateRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.AbstractTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate.exception.FilterNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractIntermediateRowFilter;
import org.xml.sax.Attributes;

import java.util.Optional;
import java.util.OptionalInt;

import static java.lang.Integer.parseInt;
import static java.util.OptionalInt.empty;

public abstract class AbstractIntermediateRowFilterTagHandler extends AbstractTagHandler {
    private static final String FILTERING_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    private final IntermediateRowFilterFactoryDictionary rowFilterFactoryDictionary;

    public AbstractIntermediateRowFilterTagHandler(final String tagName,
                                                   final IntermediateRowFilterFactoryDictionary rowFilterFactoryDictionary) {
        super(tagName);
        this.rowFilterFactoryDictionary = rowFilterFactoryDictionary;
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        final AbstractIntermediateRowFilter rowFilter = this.createFilter(context);
        context.accumulateFilter(rowFilter);
    }

    protected abstract AbstractIntermediateRowFilter createFilter(final IntermediateRowFilterFactory filterFactory,
                                                                  final int filteringCellIndex);

    protected abstract AbstractIntermediateRowFilter createFilter(final IntermediateRowFilterFactory filterFactory);

    private AbstractIntermediateRowFilter createFilter(final FuelSearchersParsingContext context) {
        final IntermediateRowFilterFactory filterFactory = this.findFilterFactory(context);
        final OptionalInt optionalFilteringCellIndex = findFilteringCellIndex(context);
        return optionalFilteringCellIndex.stream()
                .mapToObj(filteringCellIndex -> this.createFilter(filterFactory, filteringCellIndex))
                .findFirst()
                .orElseGet(() -> this.createFilter(filterFactory));
    }

    private IntermediateRowFilterFactory findFilterFactory(final FuelSearchersParsingContext context) {
        final String filterKey = context.getLastContent();
        final Optional<IntermediateRowFilterFactory> filterFactory = this.rowFilterFactoryDictionary.find(filterKey);
        return filterFactory.orElseThrow(FilterNotExistException::new);
    }

    private static OptionalInt findFilteringCellIndex(final FuelSearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTERING_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? OptionalInt.of(parseInt(cellIndex)) : empty();
    }
}
