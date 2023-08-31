package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.factory.interim.InterimFilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;

public abstract class GroupFilterFactory<F extends GroupFilter, E extends SpecificationPropertyExtractor>
        extends InterimFilterFactory<F, E> {

    public GroupFilterFactory(final E filtrationValueExtractor) {
        super(filtrationValueExtractor);
    }

}