package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.RoadGroupGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
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
