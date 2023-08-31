package by.aurorasoft.fuelinfosearcher.model.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.PloughingDepthExtractor;

public final class PloughingDepthFinalFilter extends FinalFilter {

    public PloughingDepthFinalFilter(final PloughingDepthExtractor ploughingDepthExtractor,
                                     final int filtrationCellIndex) {
        super(ploughingDepthExtractor, filtrationCellIndex);
    }

}
