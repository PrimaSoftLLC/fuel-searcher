package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelSearcherTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table-name";
        final FuelTable givenTable = createTable(givenTableName);
        final FuelSearcher givenSearcher = createSearcher(givenTable);

        final String actual = givenSearcher.findAlias();
        assertEquals(givenTableName, actual);
    }

    @Test
    public void fuelShouldBeFound() {
        final XWPFTable givenSubTable = mock(XWPFTable.class);
        throw new RuntimeException();
    }



    private static FuelTable createTable(final String name) {
        final FuelTable givenTable = mock(FuelTable.class);
        when(givenTable.getName()).thenReturn(name);
        return givenTable;
    }

    private static FuelSearcher createSearcher(final FuelTable table) {
        return new TestFuelSearcher(table, null, null, null);
    }

    private static FuelSearcher createSearcher(final XWPFTable subTable) {
        return new TestFuelSearcher(null, null, null, null, subTable);
    }

    private static final class TestFuelSearcher extends FuelSearcher {
        private final XWPFTable subTable;

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor) {
            this(table, fuelOffsetsByHeaders, filterChain, headerExtractor, null);
        }

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor,
                                final XWPFTable subTable) {
            super(table, fuelOffsetsByHeaders, filterChain, headerExtractor);
            this.subTable = subTable;
        }

        @Override
        protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                   final FuelSpecification specification) {
            return Optional.of(this.subTable);
        }
    }
}
