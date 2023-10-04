package com.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.MachineryUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import org.springframework.stereotype.Component;

@Component
public final class MachineryUnitFilterFactory extends UnitFilterFactory<MachineryUnitFilter, MachineryExtractor> {

    public MachineryUnitFilterFactory(final MachineryExtractor machineryExtractor) {
        super(machineryExtractor);
    }

    @Override
    protected MachineryUnitFilter create(final MachineryExtractor machineryExtractor, final int filtrationCellIndex) {
        return new MachineryUnitFilter(machineryExtractor, filtrationCellIndex);
    }
}
