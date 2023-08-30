package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.FilterChain;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.extractParagraphText;
import static java.lang.String.format;
import static java.util.stream.IntStream.iterate;
import static lombok.AccessLevel.PRIVATE;

/**
 * For tables with several sub tables
 */
public final class CompositeFuelSearcher extends FuelSearcher {
    private final String elementTableTitleTemplate;
    private final List<SpecificationPropertyExtractor> elementTableTitleTemplateArgumentExtractors;

    private CompositeFuelSearcher(final FuelTable fuelTable,
                                  final FuelHeaderMetadata fuelHeaderMetadata,
                                  final FilterChain filterChain,
                                  final String elementTableTitleTemplate,
                                  final List<SpecificationPropertyExtractor> elementTableTitleTemplateArgumentExtractors) {
        super(fuelTable, fuelHeaderMetadata, filterChain);
        this.elementTableTitleTemplate = elementTableTitleTemplate;
        this.elementTableTitleTemplateArgumentExtractors = elementTableTitleTemplateArgumentExtractors;
    }

    public static CompositeFuelSearcherBuilder builder() {
        return new CompositeFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final FuelTable fuelTable, final Specification specification) {
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
        //first element is paragraph(sub table's title), second element is sub table and etc.
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
        private String subTableTitleTemplate;
        private List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors;

        public void subTableTitleTemplate(final String template) {
            this.subTableTitleTemplate = template;
        }

        public void subTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
            this.createSubTableTitleTemplateArgumentExtractorsIfNecessary();
            this.subTableTitleTemplateArgumentExtractors.add(extractor);
        }

        @Override
        protected CompositeFuelSearcher build(final FuelTable fuelTable,
                                              final FuelHeaderMetadata fuelHeaderMetadata,
                                              final FilterChain filterChain) {
            return new CompositeFuelSearcher(
                    fuelTable,
                    fuelHeaderMetadata,
                    filterChain,
                    this.subTableTitleTemplate,
                    this.subTableTitleTemplateArgumentExtractors
            );
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return Stream.of(this.subTableTitleTemplate, this.subTableTitleTemplateArgumentExtractors);
        }

        private void createSubTableTitleTemplateArgumentExtractorsIfNecessary() {
            if (this.subTableTitleTemplateArgumentExtractors == null) {
                this.subTableTitleTemplateArgumentExtractors = new ArrayList<>();
            }
        }

    }
}
