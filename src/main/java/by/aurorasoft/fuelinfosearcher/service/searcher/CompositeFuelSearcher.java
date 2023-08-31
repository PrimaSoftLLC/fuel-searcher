package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.filterchain.FilterChain;
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
    private final String subTableTitleTemplate;
    private final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors;

    private CompositeFuelSearcher(final FuelTable fuelTable,
                                  final FuelHeaderMetadata fuelHeaderMetadata,
                                  final FilterChain filterChain,
                                  final String subTableTitleTemplate,
                                  final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors) {
        super(fuelTable, fuelHeaderMetadata, filterChain);
        this.subTableTitleTemplate = subTableTitleTemplate;
        this.subTableTitleTemplateArgumentExtractors = subTableTitleTemplateArgumentExtractors;
    }

    public static CompositeFuelSearcherBuilder builder() {
        return new CompositeFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements, final Specification specification) {
        return this.findSubTableTitleIndex(elements, specification)
                .stream()
                .mapToObj(titleIndex -> extractSubTableByTitleIndex(titleIndex, elements))
                .findFirst();
    }

    private OptionalInt findSubTableTitleIndex(final List<IBodyElement> elements, final Specification specification) {
        final String titleContent = this.findSubTableTitleContent(specification);
        return findTitleIndexes(elements)
                .filter(i -> Objects.equals(extractParagraphText(elements.get(i)), titleContent))
                .findFirst();
    }

    private String findSubTableTitleContent(final Specification specification) {
        final Object[] titleTemplateArguments = this.extractTitleTemplateArguments(specification);
        return format(this.subTableTitleTemplate, titleTemplateArguments);
    }

    private Object[] extractTitleTemplateArguments(final Specification specification) {
        return this.subTableTitleTemplateArgumentExtractors.stream()
                .map(extractor -> extractor.extract(specification))
                .toArray(Object[]::new);
    }

    private static IntStream findTitleIndexes(final List<IBodyElement> elements) {
        //first element is paragraph(sub table's title), second element is sub table and etc.
        return iterate(0, i -> i < elements.size(), i -> i + 2);
    }

    private static XWPFTable extractSubTableByTitleIndex(final int titleIndex, final List<IBodyElement> elements) {
        final int subTableIndex = titleIndex + 1;
        final IBodyElement element = elements.get(subTableIndex);
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
