package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.SpreadRateFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateFinalFilterFactory extends FinalFilterFactory<SpreadRateFinalFilter, SpreadRateExtractor> {

    public SpreadRateFinalFilterFactory(final SpreadRateExtractor spreadRateExtractor) {
        super(spreadRateExtractor);
    }

    @Override
    protected SpreadRateFinalFilter create(final SpreadRateExtractor spreadRateExtractor,
                                           final int filtrationCellIndex) {
        return new SpreadRateFinalFilter(spreadRateExtractor, filtrationCellIndex);
    }
}
