package com.aurorasoft.fuelsearcher.model.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.PloughingDepthExtractor;

public final class PloughingDepthFinalFilter extends FinalFilter {

    public PloughingDepthFinalFilter(final PloughingDepthExtractor ploughingDepthExtractor,
                                     final int filtrationCellIndex) {
        super(ploughingDepthExtractor, filtrationCellIndex);
    }

}