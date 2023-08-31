package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class MachineryExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "machinery";

    public MachineryExtractor() {
        super(Specification::findMachinery, PROPERTY_NAME);
    }

}
