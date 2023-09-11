package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.SpecificResistanceGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificResistanceExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class SpecificResistanceGroupFilterFactoryTest {
    private final SpecificResistanceGroupFilterFactory factory = new SpecificResistanceGroupFilterFactory(
            null
    );

    @Test
    public void filterShouldBeCreated() {
        final SpecificResistanceExtractor givenSpecificResistanceExtractor = mock(SpecificResistanceExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final SpecificResistanceGroupFilter actual = this.factory.create(
                givenSpecificResistanceExtractor, givenFiltrationCellIndex
        );

        assertSame(givenSpecificResistanceExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
