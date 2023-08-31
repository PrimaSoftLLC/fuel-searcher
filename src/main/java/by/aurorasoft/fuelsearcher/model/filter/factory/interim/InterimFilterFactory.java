package by.aurorasoft.fuelsearcher.model.filter.factory.interim;

import by.aurorasoft.fuelsearcher.model.filter.factory.FilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class InterimFilterFactory<F extends InterimFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public InterimFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
