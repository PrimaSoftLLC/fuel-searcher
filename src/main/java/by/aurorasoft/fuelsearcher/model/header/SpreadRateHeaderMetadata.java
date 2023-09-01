package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateHeaderMetadata extends FuelHeaderMetadata {

    public SpreadRateHeaderMetadata(@Value("${spread-rate}") final String[] values,
                                    final SpreadRateExtractor spreadRateExtractor) {
        super(values, spreadRateExtractor);
    }

}
