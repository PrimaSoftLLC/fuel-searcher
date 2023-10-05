package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.FertilizerTypeGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.FertilizerTypeExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class FertilizerTypeGroupFilterFactoryTest {
    private final FertilizerTypeGroupFilterFactory factory = new FertilizerTypeGroupFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final FertilizerTypeExtractor givenFertilizerTypeExtractor = mock(FertilizerTypeExtractor.class);
        final int givenFiltrationCellIndex = 5;

        final FertilizerTypeGroupFilter actual = this.factory.create(
                givenFertilizerTypeExtractor,
                givenFiltrationCellIndex
        );

        assertSame(givenFertilizerTypeExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }

}
