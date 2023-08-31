package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.service.searcher.filterchain.FilterChain;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

public final class SimpleFuelSearcherTest {

    @Test
    public void subTableShouldBeFound()
            throws Exception {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final IBodyElement firstElement = mock(IBodyElement.class);
        final List<IBodyElement> givenElements = List.of(firstElement, mock(IBodyElement.class));
        final Specification givenSpecification = mock(Specification.class);

        final IBodyElement actual = 
    }

    private static SimpleFuelSearcher createSearcher()
            throws Exception {
        final Constructor<SimpleFuelSearcher> constructor = SimpleFuelSearcher.class.getDeclaredConstructor(
                FuelTable.class, FuelHeaderMetadata.class, FilterChain.class
        );
        constructor.setAccessible(true);
        try {
            final FuelHeaderMetadata fuelHeaderMetadata = createFuelHeaderMetaData();
            return constructor.newInstance(null, fuelHeaderMetadata, null);
        } finally {
            constructor.setAccessible(false);
        }
    }

    private static FuelHeaderMetadata createFuelHeaderMetaData() {
        return FuelHeaderMetadata.builder()
                .name("fuel-header")
                .values(emptyList())
                .build();
    }
}
