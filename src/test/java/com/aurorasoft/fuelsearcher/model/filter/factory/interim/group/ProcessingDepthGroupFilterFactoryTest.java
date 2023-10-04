package com.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import com.aurorasoft.fuelsearcher.model.filter.interim.group.ProcessingDepthGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ProcessingDepthExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class ProcessingDepthGroupFilterFactoryTest {
    private final ProcessingDepthGroupFilterFactory factory = new ProcessingDepthGroupFilterFactory(
            null
    );

    @Test
    public void filterShouldBeCreated() {
        final ProcessingDepthExtractor givenProcessingDepthExtractor = mock(ProcessingDepthExtractor.class);
        final int givenFiltrationCellIndex = 3;

        final ProcessingDepthGroupFilter actual = this.factory.create(
                givenProcessingDepthExtractor,
                givenFiltrationCellIndex
        );

        assertSame(givenProcessingDepthExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }

}
