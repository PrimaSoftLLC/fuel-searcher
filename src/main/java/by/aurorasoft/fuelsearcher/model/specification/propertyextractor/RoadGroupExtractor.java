package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class RoadGroupExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "группа дорог";

    public RoadGroupExtractor() {
        super(FuelSpecification::findRoadGroup, PROPERTY_NAME);
    }
}
