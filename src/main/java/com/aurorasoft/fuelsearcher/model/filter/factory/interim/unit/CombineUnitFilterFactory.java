package com.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.CombineUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CombineExtractor;
import org.springframework.stereotype.Component;

@Component
public final class CombineUnitFilterFactory extends UnitFilterFactory<CombineUnitFilter, CombineExtractor> {

    public CombineUnitFilterFactory(final CombineExtractor combineExtractor) {
        super(combineExtractor);
    }

    @Override
    protected CombineUnitFilter create(final CombineExtractor combineExtractor, final int filtrationCellIndex) {
        return new CombineUnitFilter(combineExtractor, filtrationCellIndex);
    }
}
