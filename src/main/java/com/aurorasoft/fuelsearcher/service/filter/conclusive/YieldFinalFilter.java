package com.aurorasoft.fuelsearcher.service.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.YieldExtractor;

public final class YieldFinalFilter extends FinalFilter {

    public YieldFinalFilter(final YieldExtractor yieldExtractor, final int filtrationCellIndex) {
        super(yieldExtractor, filtrationCellIndex);
    }

}
