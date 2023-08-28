package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.*;
import java.util.stream.IntStream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.extractParagraphText;
import static java.lang.String.format;
import static java.util.stream.IntStream.iterate;
import static lombok.AccessLevel.PRIVATE;

/**
 * For tables with several element-tables
 */
public final class CompositeFuelSearcher extends FuelSearcher {
    private final String elementTableTitleTemplate;
    private final List<SpecificationPropertyExtractor> elementTableTitleTemplateArgumentExtractors;

    public CompositeFuelSearcher(final FuelTable fuelTable,
                                 final List<String> fuelHeaders,
                                 final RowFilterChain filterChain,
                                 final SpecificationPropertyExtractor fuelHeaderCellValueExtractor,
                                 final String elementTableTitleTemplate,
                                 final List<SpecificationPropertyExtractor> elementTableTitleTemplateArgumentExtractors) {
        super(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
        this.elementTableTitleTemplate = elementTableTitleTemplate;
        this.elementTableTitleTemplateArgumentExtractors = elementTableTitleTemplateArgumentExtractors;
    }

    public static CompositeFuelSearcherBuilder builder() {
        return new CompositeFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                   final Specification specification) {
        final List<IBodyElement> fuelTableElements = fuelTable.getElements();
        return this.findTitleIndex(fuelTableElements, specification)
                .stream()
                .mapToObj(titleIndex -> extractElementTableByTitleIndex(titleIndex, fuelTableElements))
                .findFirst();
    }

    private OptionalInt findTitleIndex(final List<IBodyElement> fuelTableElements,
                                       final Specification specification) {
        final String titleContent = this.findTitleContent(specification);
        return findTitleIndexes(fuelTableElements)
                .filter(i -> Objects.equals(extractParagraphText(fuelTableElements.get(i)), titleContent))
                .findFirst();
    }

    private String findTitleContent(final Specification specification) {
        final Object[] titleTemplateArguments = this.extractTitleTemplateArguments(specification);
        return format(this.elementTableTitleTemplate, titleTemplateArguments);
    }

    private Object[] extractTitleTemplateArguments(final Specification specification) {
        return this.elementTableTitleTemplateArgumentExtractors.stream()
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

    @NoArgsConstructor(access = PRIVATE)
    public static final class CompositeFuelSearcherBuilder extends FuelSearcherBuilder<CompositeFuelSearcher> {
        private String elementTableTitleTemplate;
        private final List<SpecificationPropertyExtractor> elementTableTitleTemplateArgumentExtractors = new ArrayList<>();

        public CompositeFuelSearcherBuilder subTableTitleTemplate(final String template) {
            this.elementTableTitleTemplate = template;
            return this;
        }

        public CompositeFuelSearcherBuilder subTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
            this.elementTableTitleTemplateArgumentExtractors.add(extractor);
            return this;
        }

        @Override
        protected CompositeFuelSearcher build(final FuelTable fuelTable,
                                              final List<String> fuelHeaders,
                                              final RowFilterChain filterChain,
                                              final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
            return new CompositeFuelSearcher(
                    fuelTable,
                    fuelHeaders,
                    filterChain,
                    fuelHeaderCellValueExtractor,
                    this.elementTableTitleTemplate,
                    this.elementTableTitleTemplateArgumentExtractors
            );
        }

    }
}
