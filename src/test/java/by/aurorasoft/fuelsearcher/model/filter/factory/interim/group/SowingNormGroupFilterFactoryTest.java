package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.SowingNormGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SowingNormExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class SowingNormGroupFilterFactoryTest {
    private final SowingNormGroupFilterFactory factory = new SowingNormGroupFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final SowingNormExtractor givenExtractor = mock(SowingNormExtractor.class);
        final int givenFiltrationCellIndex = 0;

        final SowingNormGroupFilter actual = this.factory.create(givenExtractor, givenFiltrationCellIndex);

        assertSame(givenExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}