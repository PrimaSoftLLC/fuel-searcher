package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.unit.TractorUnitFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TractorExtractor;
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
