package com.aurorasoft.fuelsearcher.service.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CorpusCountExtractor;

public final class CorpusCountUnitFilter extends UnitFilter {

    public CorpusCountUnitFilter(final CorpusCountExtractor corpusCountExtractor, final int filtrationCellIndex) {
        super(corpusCountExtractor, filtrationCellIndex);
    }
}
