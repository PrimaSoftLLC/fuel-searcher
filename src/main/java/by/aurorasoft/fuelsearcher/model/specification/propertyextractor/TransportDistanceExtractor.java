package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class TransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "расстояние транспортировки";

    public TransportDistanceExtractor() {
        super(FuelSpecification::findTransportDistance, PROPERTY_NAME);
    }
}
