package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.group.RoadGroupGroupFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.springframework.stereotype.Component;

@Component
public final class RoadGroupGroupFilterFactory extends GroupFilterFactory<RoadGroupGroupFilter, RoadGroupExtractor> {

    public RoadGroupGroupFilterFactory(final RoadGroupExtractor roadGroupExtractor) {
        super(roadGroupExtractor);
    }

    @Override
    protected RoadGroupGroupFilter create(final RoadGroupExtractor roadGroupExtractor, final int filtrationCellIndex) {
        return new RoadGroupGroupFilter(roadGroupExtractor, filtrationCellIndex);
    }
}
