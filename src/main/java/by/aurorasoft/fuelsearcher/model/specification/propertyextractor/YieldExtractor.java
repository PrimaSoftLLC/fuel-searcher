package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class YieldExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "урожайность";

    public YieldExtractor() {
        super(Specification::findYield, PROPERTY_NAME);
    }

}
