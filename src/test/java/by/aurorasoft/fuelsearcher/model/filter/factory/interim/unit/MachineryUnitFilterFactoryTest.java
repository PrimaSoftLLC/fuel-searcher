package by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.MachineryUnitFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class MachineryUnitFilterFactoryTest {
    private final MachineryUnitFilterFactory factory = new MachineryUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final MachineryExtractor givenMachineryExtractor = mock(MachineryExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final MachineryUnitFilter actual = this.factory.create(givenMachineryExtractor, givenFiltrationCellIndex);

        assertSame(givenMachineryExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
