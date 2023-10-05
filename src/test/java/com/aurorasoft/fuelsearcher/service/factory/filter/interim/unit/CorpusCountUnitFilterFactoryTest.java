package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.service.filter.interim.unit.CorpusCountUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CorpusCountExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class CorpusCountUnitFilterFactoryTest {
    private final CorpusCountUnitFilterFactory factory = new CorpusCountUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final CorpusCountExtractor givenCorpusCountExtractor = mock(CorpusCountExtractor.class);
        final int givenFiltrationCellIndex = 5;

        final CorpusCountUnitFilter actual = this.factory.create(givenCorpusCountExtractor, givenFiltrationCellIndex);

        assertSame(givenCorpusCountExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
