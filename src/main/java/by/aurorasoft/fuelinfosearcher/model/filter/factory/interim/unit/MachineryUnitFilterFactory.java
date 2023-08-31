package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.unit.MachineryUnitFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.MachineryExtractor;
import org.springframework.stereotype.Component;

@Component
public final class MachineryUnitFilterFactory extends UnitFilterFactory<MachineryUnitFilter, MachineryExtractor> {

    public MachineryUnitFilterFactory(final MachineryExtractor machineryExtractor) {
        super(machineryExtractor);
    }

    @Override
    protected MachineryUnitFilter create(final MachineryExtractor machineryExtractor, final int filtrationCellIndex) {
        return new MachineryUnitFilter(machineryExtractor, filtrationCellIndex);
    }
}
