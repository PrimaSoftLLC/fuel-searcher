package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class ChargingMethodAndTransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "chargingMethodAndTransportDistance";

    public ChargingMethodAndTransportDistanceExtractor() {
        super(Specification::findChargingMethodAndTransportDistance, PROPERTY_NAME);
    }

}
