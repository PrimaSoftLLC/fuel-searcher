package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class MachineryExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "механизм";

    public MachineryExtractor() {
        super(FuelSpecification::findMachinery, PROPERTY_NAME);
    }

}
