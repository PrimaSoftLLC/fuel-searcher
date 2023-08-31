package by.aurorasoft.fuelsearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.factory.FilterFactory;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class FinalFilterFactory<F extends FinalFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public FinalFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
