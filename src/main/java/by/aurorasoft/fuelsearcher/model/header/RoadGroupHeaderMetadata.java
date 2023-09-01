package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RoadGroupHeaderMetadata extends FuelHeaderMetadata {

    public RoadGroupHeaderMetadata(@Value("${fuel-header-metadata.values.road-group}") final String[] values,
                                   final RoadGroupExtractor roadGroupExtractor) {
        super(values, roadGroupExtractor);
    }

}
