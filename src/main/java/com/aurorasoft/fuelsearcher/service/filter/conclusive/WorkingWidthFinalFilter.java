package com.aurorasoft.fuelsearcher.service.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.WorkingWidthExtractor;

public final class WorkingWidthFinalFilter extends FinalFilter {

    public WorkingWidthFinalFilter(final WorkingWidthExtractor workingWidthExtractor,
                                   final int filtrationCellIndex) {
        super(workingWidthExtractor, filtrationCellIndex);
    }

}
