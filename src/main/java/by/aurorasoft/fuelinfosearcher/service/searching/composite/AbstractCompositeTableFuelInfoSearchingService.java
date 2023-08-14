package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelInfoSearchingService;
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
public abstract class AbstractCompositeTableFuelInfoSearchingService extends AbstractTableFuelInfoSearchingService {

    public AbstractCompositeTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                          final String fuelTableName,
                                                          final String[] fuelInfoHeaders,
                                                          final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, fuelInfoHeaders, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                         final FuelInfoSpecification specification) {
        final List<IBodyElement> fuelTableElements = fuelTable.getElements();
        return this.findTitleIndex(fuelTableElements, specification)
                .stream()
                .mapToObj(titleIndex -> extractElementTableByTitleIndex(titleIndex, fuelTableElements))
                .findFirst();
    }

    protected abstract String findElementTableTitleTemplate();

    //TODO: do result as String[]
    protected abstract Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors();

    private OptionalInt findTitleIndex(final List<IBodyElement> fuelTableElements,
                                       final FuelInfoSpecification specification) {
        final String titleContent = this.findTitleContent(specification);
        return findTitleIndexes(fuelTableElements)
                .filter(i -> Objects.equals(extractParagraphText(fuelTableElements.get(i)), titleContent))
                .findFirst();
    }

    private String findTitleContent(final FuelInfoSpecification specification) {
        final String titleTemplate = this.findElementTableTitleTemplate();
        final Object[] titleTemplateArguments = this.extractTitleTemplateArguments(specification);
        return format(titleTemplate, titleTemplateArguments);
    }

    private Object[] extractTitleTemplateArguments(final FuelInfoSpecification specification) {
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
