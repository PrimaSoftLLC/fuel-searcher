package com.aurorasoft.fuelsearcher.service.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;

public final class MachineryUnitFilter extends UnitFilter {

    public MachineryUnitFilter(final MachineryExtractor machineryExtractor, final int filtrationCellIndex) {
        super(machineryExtractor, filtrationCellIndex);
    }
}
