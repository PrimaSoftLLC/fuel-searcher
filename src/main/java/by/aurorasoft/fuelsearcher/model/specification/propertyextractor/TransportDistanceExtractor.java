package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class TransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "расстояние транспортировки";

    public TransportDistanceExtractor() {
        super(Specification::findTransportDistance, PROPERTY_NAME);
    }
}
