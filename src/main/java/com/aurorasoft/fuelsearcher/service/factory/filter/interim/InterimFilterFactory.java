package com.aurorasoft.fuelsearcher.service.factory.filter.interim;

import com.aurorasoft.fuelsearcher.service.factory.filter.FilterFactory;
import com.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class InterimFilterFactory<F extends InterimFilter, E extends SpecificationPropertyExtractor>
        extends FilterFactory<F, E> {

    public InterimFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
