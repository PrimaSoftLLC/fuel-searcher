package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.SpecificResistanceGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificResistanceExtractor;
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
