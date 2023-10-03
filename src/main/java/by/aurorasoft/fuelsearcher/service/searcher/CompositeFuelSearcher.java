package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.areEqualConsideringOnlyLettersAndDigits;
import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractParagraphText;
import static java.lang.String.format;
import static java.util.stream.IntStream.iterate;
import static lombok.AccessLevel.PRIVATE;

/**
 * For tables with several sub tables
 */
public final class CompositeFuelSearcher extends FuelSearcher {
    private final SubTableTitleMetadata subTableTitleMetadata;

    private CompositeFuelSearcher(final FuelTable table,
                                  final FuelHeaderMetadata headerMetadata,
                                  final FilterChain filterChain,
                                  final SubTableTitleMetadata subTableTitleMetadata) {
        super(table, headerMetadata, filterChain);
        this.subTableTitleMetadata = subTableTitleMetadata;
    }

    public static CompositeSearcherBuilder builder() {
        return new CompositeSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements, final FuelSpecification specification) {
        return this.findSubTableTitleIndex(elements, specification)
                .stream()
                .mapToObj(titleIndex -> extractSubTableByTitleIndex(titleIndex, elements))
                .findFirst();
    }

    @Override
    protected Stream<? extends PropertyMetadataSource> findAdditionalPropertyMetadataSources() {
        return this.subTableTitleMetadata
                .getArgumentsMetadata()
                .stream();
    }

    private OptionalInt findSubTableTitleIndex(final List<IBodyElement> elements, final FuelSpecification specification) {
        final String titleContent = this.findSubTableTitleContent(specification);
        return findTitleIndexes(elements)
                .filter(i -> areEqualConsideringOnlyLettersAndDigits(extractParagraphText(elements.get(i)), titleContent))
                .findFirst();
    }

    private String findSubTableTitleContent(final FuelSpecification specification) {
        final String subTableTitleTemplate = this.subTableTitleMetadata.getTemplateWithStringFillers();
        final Object[] titleTemplateArguments = this.extractTitleTemplateArguments(specification);
        return format(subTableTitleTemplate, titleTemplateArguments);
    }

    private Object[] extractTitleTemplateArguments(final FuelSpecification specification) {
        return this.subTableTitleMetadata.findArgumentsExtractors()
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
                + "tables should be located in even indexes";

        private SubTableTitleMetadata subTableTitleMetadata;

        public void subTableTitleMetadata(final SubTableTitleMetadata metadata) {
            this.subTableTitleMetadata = metadata;
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
        protected CompositeFuelSearcher build(final FuelTable table,
                                              final FuelHeaderMetadata headerMetadata,
                                              final FilterChain filterChain) {
            return new CompositeFuelSearcher(table, headerMetadata, filterChain, this.subTableTitleMetadata);
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return Stream.of(this.subTableTitleMetadata);
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
