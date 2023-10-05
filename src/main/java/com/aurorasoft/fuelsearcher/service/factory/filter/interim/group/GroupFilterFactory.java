package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.InterimFilterFactory;
import com.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class GroupFilterFactory<F extends GroupFilter, E extends SpecificationPropertyExtractor>
        extends InterimFilterFactory<F, E> {

    public GroupFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}
