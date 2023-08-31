package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.model.filter.interim.unit.UnitFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class UnitFilterFactory<F extends UnitFilter, E extends SpecificationPropertyExtractor>
        extends InterimFilterFactory<F, E> {

    public UnitFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
