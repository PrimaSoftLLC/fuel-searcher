package by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelinfosearcher.model.filter.conclusive.YieldFinalFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.YieldExtractor;
import org.springframework.stereotype.Component;

@Component
public final class YieldFinalFilterFactory extends FinalFilterFactory<YieldFinalFilter, YieldExtractor> {

    public YieldFinalFilterFactory(final YieldExtractor filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

    @Override
    protected YieldFinalFilter create(final YieldExtractor yieldExtractor, final int filtrationCellIndex) {
        return new YieldFinalFilter(yieldExtractor, filtrationCellIndex);
    }
}
