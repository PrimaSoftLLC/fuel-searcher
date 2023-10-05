package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.YieldFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.YieldExtractor;
import org.springframework.stereotype.Component;

@Component
public final class YieldFinalFilterFactory extends FinalFilterFactory<YieldFinalFilter, YieldExtractor> {

    public YieldFinalFilterFactory(final YieldExtractor yieldExtractor) {
        super(yieldExtractor);
    }

    @Override
    protected YieldFinalFilter create(final YieldExtractor yieldExtractor, final int filtrationCellIndex) {
        return new YieldFinalFilter(yieldExtractor, filtrationCellIndex);
    }
}
