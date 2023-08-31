package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.service.searcher.filterchain.FilterChain;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;
import static lombok.AccessLevel.PRIVATE;


/**
 * For tables with one sub tables
 */
public final class SimpleFuelSearcher extends FuelSearcher {

    private SimpleFuelSearcher(final FuelTable fuelTable,
                               final FuelHeaderMetadata fuelHeaderMetadata,
                               final FilterChain filterChain) {
        super(fuelTable, fuelHeaderMetadata, filterChain);
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
    public static final class SimpleSearcherBuilder extends FuelSearcherBuilder<SimpleFuelSearcher> {

        @Override
        protected SimpleFuelSearcher build(final FuelTable fuelTable,
                                           final FuelHeaderMetadata fuelHeaderMetadata,
                                           final FilterChain filterChain) {
            return new SimpleFuelSearcher(fuelTable, fuelHeaderMetadata, filterChain);
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return empty();
        }

    }
}
