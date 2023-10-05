package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.service.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.service.factory.filter.FilterFactory;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class FinalFilterFactory<F extends FinalFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public FinalFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
