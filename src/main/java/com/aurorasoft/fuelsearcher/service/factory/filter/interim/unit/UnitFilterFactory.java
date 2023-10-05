package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.InterimFilterFactory;
import com.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class UnitFilterFactory<F extends UnitFilter, E extends SpecificationPropertyExtractor>
        extends InterimFilterFactory<F, E> {

    public UnitFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
