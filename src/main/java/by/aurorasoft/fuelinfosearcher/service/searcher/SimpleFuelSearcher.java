package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.filterchain.FilterChain;
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

    public static SimpleFuelSearcherBuilder builder() {
        return new SimpleFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements, final Specification specification) {
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable subTable = (XWPFTable) firstElement;
        return Optional.of(subTable);
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class SimpleFuelSearcherBuilder extends FuelSearcherBuilder<SimpleFuelSearcher> {

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
