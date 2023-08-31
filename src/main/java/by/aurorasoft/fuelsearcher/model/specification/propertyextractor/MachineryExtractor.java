package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class MachineryExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "механизм";

    public MachineryExtractor() {
        super(Specification::findMachinery, PROPERTY_NAME);
    }

}
