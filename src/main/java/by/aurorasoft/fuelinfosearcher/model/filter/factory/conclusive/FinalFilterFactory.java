package by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelinfosearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.model.filter.factory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class FinalFilterFactory<F extends FinalFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public FinalFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
