package com.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.RowWidthUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RowWidthExtractor;
import org.springframework.stereotype.Component;

@Component
public final class RowWidthUnitFilterFactory extends UnitFilterFactory<RowWidthUnitFilter, RowWidthExtractor> {

    public RowWidthUnitFilterFactory(final RowWidthExtractor rowWidthExtractor) {
        super(rowWidthExtractor);
    }

    @Override
    protected RowWidthUnitFilter create(final RowWidthExtractor rowWidthExtractor, final int filtrationCellIndex) {
        return new RowWidthUnitFilter(rowWidthExtractor, filtrationCellIndex);
    }
}
