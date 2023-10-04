package com.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.TractorUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TractorExtractor;
import org.springframework.stereotype.Component;

@Component
public final class TractorUnitFilterFactory extends UnitFilterFactory<TractorUnitFilter, TractorExtractor> {

    public TractorUnitFilterFactory(final TractorExtractor tractorExtractor) {
        super(tractorExtractor);
    }

    @Override
    protected TractorUnitFilter create(final TractorExtractor tractorExtractor, int filtrationCellIndex) {
        return new TractorUnitFilter(tractorExtractor, filtrationCellIndex);
    }
}