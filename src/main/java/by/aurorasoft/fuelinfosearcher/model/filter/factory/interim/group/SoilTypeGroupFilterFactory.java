package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.group.SoilTypeGroupFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SoilTypeExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SoilTypeGroupFilterFactory extends GroupFilterFactory<SoilTypeGroupFilter, SoilTypeExtractor> {

    public SoilTypeGroupFilterFactory(final SoilTypeExtractor soilTypeExtractor) {
        super(soilTypeExtractor);
    }

    @Override
    protected SoilTypeGroupFilter create(final SoilTypeExtractor soilTypeExtractor, final int filtrationCellIndex) {
        return new SoilTypeGroupFilter(soilTypeExtractor, filtrationCellIndex);
    }
}
