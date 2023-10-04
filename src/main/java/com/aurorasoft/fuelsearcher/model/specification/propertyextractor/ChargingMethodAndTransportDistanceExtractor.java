package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class ChargingMethodAndTransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "способ загрузки удобрений и расстояние транспортировки";

    public ChargingMethodAndTransportDistanceExtractor() {
        super(FuelSpecification::findChargingMethodAndTransportDistance, PROPERTY_NAME);
    }

}
