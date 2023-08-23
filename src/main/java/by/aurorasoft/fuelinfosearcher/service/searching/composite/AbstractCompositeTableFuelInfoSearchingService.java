package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.extractParagraphText;
import static java.lang.String.format;
import static java.util.stream.IntStream.iterate;

/**
 * For tables with several element-tables
 */
public abstract class AbstractCompositeTableFuelInfoSearchingService extends AbstractTableFuelSearcher {

    public AbstractCompositeTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                          final String fuelTableName,
                                                          final String[] fuelHeaders,
                                                          final RowFilterChain filterChain,
                                                          final Function<FuelSpecification, String> fuelHeaderCellValueExtractor) {
        super(fuelDocument, fuelTableName, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
    }

    @Override
    protected final Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                         final FuelSpecification specification) {
        final List<IBodyElement> fuelTableElements = fuelTable.getElements();
        return this.findTitleIndex(fuelTableElements, specification)
                .stream()
                .mapToObj(titleIndex -> extractElementTableByTitleIndex(titleIndex, fuelTableElements))
                .findFirst();
    }

    protected abstract String findElementTableTitleTemplate();

    //TODO: do result as String[]
    protected abstract Stream<Function<FuelSpecification, String>> findElementTableTitleTemplateArgumentExtractors();

    private OptionalInt findTitleIndex(final List<IBodyElement> fuelTableElements,
                                       final FuelSpecification specification) {
        final String titleContent = this.findTitleContent(specification);
        return findTitleIndexes(fuelTableElements)
                .filter(i -> Objects.equals(extractParagraphText(fuelTableElements.get(i)), titleContent))
                .findFirst();
    }

    private String findTitleContent(final FuelSpecification specification) {
        final String titleTemplate = this.findElementTableTitleTemplate();
        final Object[] titleTemplateArguments = this.extractTitleTemplateArguments(specification);
        return format(titleTemplate, titleTemplateArguments);
    }

    private Object[] extractTitleTemplateArguments(final FuelSpecification specification) {
        return this.findElementTableTitleTemplateArgumentExtractors()
                .map(extractor -> extractor.apply(specification))
                .toArray(Object[]::new);
    }

    private static IntStream findTitleIndexes(final List<IBodyElement> fuelTableElements) {
        //first element is paragraph, second element is element-table and etc.
        return iterate(0, i -> i < fuelTableElements.size(), i -> i + 2);
    }

    private static XWPFTable extractElementTableByTitleIndex(final int titleIndex,
                                                             final List<IBodyElement> fuelTableElements) {
        final int elementTableIndex = titleIndex + 1;
        final IBodyElement element = fuelTableElements.get(elementTableIndex);
        return (XWPFTable) element;
    }

}
