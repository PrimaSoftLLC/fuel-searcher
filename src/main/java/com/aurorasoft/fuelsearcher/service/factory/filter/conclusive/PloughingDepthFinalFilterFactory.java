package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.PloughingDepthFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.PloughingDepthExtractor;
import org.springframework.stereotype.Component;

@Component
public final class PloughingDepthFinalFilterFactory
        extends FinalFilterFactory<PloughingDepthFinalFilter, PloughingDepthExtractor> {

    public PloughingDepthFinalFilterFactory(final PloughingDepthExtractor ploughingDepthExtractor) {
        super(ploughingDepthExtractor);
    }

    @Override
    protected PloughingDepthFinalFilter create(final PloughingDepthExtractor ploughingDepthExtractor,
                                               final int filtrationCellIndex) {
        return new PloughingDepthFinalFilter(ploughingDepthExtractor, filtrationCellIndex);
    }
}
