package by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.WorkingWidthUnitFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.WorkingWidthExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class WorkingWidthUnitFilterFactoryTest {
    private final WorkingWidthUnitFilterFactory factory = new WorkingWidthUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final WorkingWidthExtractor givenWorkingWidthExtractor = mock(WorkingWidthExtractor.class);
        final int givenFiltrationCellIndex = 5;

        final WorkingWidthUnitFilter actual = this.factory.create(givenWorkingWidthExtractor, givenFiltrationCellIndex);

        assertSame(givenWorkingWidthExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
