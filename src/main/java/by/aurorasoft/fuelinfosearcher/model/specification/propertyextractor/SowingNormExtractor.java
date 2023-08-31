package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class SowingNormExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "sowingNorm";

    public SowingNormExtractor() {
        super(Specification::findSowingNorm, PROPERTY_NAME);
    }

}
