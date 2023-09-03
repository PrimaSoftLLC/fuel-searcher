package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;
import static lombok.AccessLevel.PRIVATE;


/**
 * For tables with one sub tables
 */
public final class SimpleFuelSearcher extends FuelSearcher {

    private SimpleFuelSearcher(final FuelTable fuelTable,
                               final Map<String, Integer> fuelOffsetsByHeaders,
                               final FilterChain filterChain,
                               final SpecificationPropertyExtractor fuelHeaderExtractor) {
        super(fuelTable, fuelOffsetsByHeaders, filterChain, fuelHeaderExtractor);
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements, final Specification specification) {
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable subTable = (XWPFTable) firstElement;
        return Optional.of(subTable);
    }

    public static SimpleSearcherBuilder builder() {
        return new SimpleSearcherBuilder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class SimpleSearcherBuilder extends SearcherBuilder<SimpleFuelSearcher> {
        private static final String NOT_VALID_ELEMENTS_MESSAGE = "Fuel table should contain only one table-element";

        @Override
        protected boolean isValidElements(final List<IBodyElement> elements) {
            return isValidSize(elements) && isFirstTable(elements);
        }

        @Override
        protected String findNotValidElementsMessage() {
            return NOT_VALID_ELEMENTS_MESSAGE;
        }

        @Override
        protected SimpleFuelSearcher build(final FuelTable fuelTable,
                                           final Map<String, Integer> fuelOffsetsByHeaders,
                                           final FilterChain filterChain,
                                           final SpecificationPropertyExtractor fuelHeaderExtractor) {
            return new SimpleFuelSearcher(fuelTable, fuelOffsetsByHeaders, filterChain, fuelHeaderExtractor);
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return empty();
        }

        private static boolean isValidSize(final List<IBodyElement> elements) {
            return elements.size() == 1;
        }

        private static boolean isFirstTable(final List<IBodyElement> elements) {
            final IBodyElement firstElement = elements.get(0);
            return firstElement instanceof XWPFTable;
        }
    }
}
