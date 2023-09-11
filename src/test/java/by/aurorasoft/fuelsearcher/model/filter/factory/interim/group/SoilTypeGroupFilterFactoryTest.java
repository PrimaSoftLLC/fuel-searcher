package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.SoilTypeGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SoilTypeExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class SoilTypeGroupFilterFactoryTest {
    private final SoilTypeGroupFilterFactory factory = new SoilTypeGroupFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final SoilTypeExtractor givenSoilTypeExtractor = mock(SoilTypeExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final SoilTypeGroupFilter actual = this.factory.create(givenSoilTypeExtractor, givenFiltrationCellIndex);

        assertSame(givenSoilTypeExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
