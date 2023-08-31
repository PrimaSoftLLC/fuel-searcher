package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CombineExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "комбайн";

    public CombineExtractor() {
        super(Specification::findCombine, PROPERTY_NAME);
    }
}
