package com.aurorasoft.fuelsearcher.model.filter.factory.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.model.filter.factory.FilterFactory;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class FinalFilterFactory<F extends FinalFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public FinalFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
