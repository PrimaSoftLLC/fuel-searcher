package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractParagraphText;
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
                                  final Map<String, Integer> fuelOffsetsByHeaders,
                                  final FilterChain filterChain,
                                  final SpecificationPropertyExtractor fuelHeaderExtractor,
                                  final String subTableTitleTemplate,
                                  final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors) {
        super(fuelTable, fuelOffsetsByHeaders, filterChain, fuelHeaderExtractor);
        this.subTableTitleTemplate = subTableTitleTemplate;
        this.subTableTitleTemplateArgumentExtractors = subTableTitleTemplateArgumentExtractors;
    }

    public static CompositeSearcherBuilder builder() {
        return new CompositeSearcherBuilder();
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
        return findIndexesAfterOne(elements, 0);
    }

    private static IntStream findIndexesAfterOne(final List<IBodyElement> elements, final int firstIndex) {
        return iterate(firstIndex, i -> i < elements.size(), i -> i + 2);
    }

    private static XWPFTable extractSubTableByTitleIndex(final int titleIndex, final List<IBodyElement> elements) {
        final int subTableIndex = titleIndex + 1;
        final IBodyElement element = elements.get(subTableIndex);
        return (XWPFTable) element;
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class CompositeSearcherBuilder extends SearcherBuilder<CompositeFuelSearcher> {
        private static final String NOT_VALID_ELEMENTS_MESSAGE = "Paragraphs should be located in not even indexes, "
                + "tables should be located in even index";

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
        protected boolean isValidElements(final List<IBodyElement> elements) {
            return areParagraphsOnEvenIndexes(elements) && areTablesOnNotEvenIndexes(elements);
        }

        @Override
        protected String findNotValidElementsMessage() {
            return NOT_VALID_ELEMENTS_MESSAGE;
        }

        @Override
        protected CompositeFuelSearcher build(final FuelTable fuelTable,
                                              final Map<String, Integer> fuelOffsetsByHeaders,
                                              final FilterChain filterChain,
                                              final SpecificationPropertyExtractor fuelHeaderExtractor) {
            return new CompositeFuelSearcher(
                    fuelTable,
                    fuelOffsetsByHeaders,
                    filterChain,
                    fuelHeaderExtractor,
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

        private static boolean areParagraphsOnEvenIndexes(final List<IBodyElement> elements) {
            return areInstancesAfterOne(elements, 0, XWPFParagraph.class);
        }

        private static boolean areTablesOnNotEvenIndexes(final List<IBodyElement> elements) {
            return areInstancesAfterOne(elements, 1, XWPFTable.class);
        }

        private static <T extends IBodyElement> boolean areInstancesAfterOne(final List<IBodyElement> elements,
                                                                             final int firstElementIndex,
                                                                             final Class<T> type) {
            return findIndexesAfterOne(elements, firstElementIndex)
                    .mapToObj(elements::get)
                    .allMatch(type::isInstance);
        }
    }
}
