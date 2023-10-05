package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.SpecificResistanceGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificResistanceExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SpecificResistanceGroupFilterFactory
        extends GroupFilterFactory<SpecificResistanceGroupFilter, SpecificResistanceExtractor> {

    public SpecificResistanceGroupFilterFactory(final SpecificResistanceExtractor specificResistanceExtractor) {
        super(specificResistanceExtractor);
    }

    @Override
    protected SpecificResistanceGroupFilter create(final SpecificResistanceExtractor specificResistanceExtractor,
                                                   final int filtrationCellIndex) {
        return new SpecificResistanceGroupFilter(specificResistanceExtractor, filtrationCellIndex);
    }
}
