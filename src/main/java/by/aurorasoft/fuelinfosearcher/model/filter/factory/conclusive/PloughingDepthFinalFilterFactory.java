package by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelinfosearcher.model.filter.conclusive.PloughingDepthFinalFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.PloughingDepthExtractor;
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
