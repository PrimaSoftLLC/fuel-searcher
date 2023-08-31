package by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelinfosearcher.model.filter.conclusive.SpreadRateFinalFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateFinalFilterFactory extends FinalFilterFactory<SpreadRateFinalFilter, SpreadRateExtractor> {

    public SpreadRateFinalFilterFactory(final SpreadRateExtractor spreadRateExtractor) {
        super(spreadRateExtractor);
    }

    @Override
    protected SpreadRateFinalFilter create(final SpreadRateExtractor spreadRateExtractor, int filtrationCellIndex) {
        return new SpreadRateFinalFilter(spreadRateExtractor, filtrationCellIndex);
    }
}
