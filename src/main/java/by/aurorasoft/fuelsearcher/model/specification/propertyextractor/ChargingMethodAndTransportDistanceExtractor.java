package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class ChargingMethodAndTransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "способ загрузки удобрений и расстояние транспортировки";

    public ChargingMethodAndTransportDistanceExtractor() {
        super(Specification::findChargingMethodAndTransportDistance, PROPERTY_NAME);
    }

}
