package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class SoilTypeExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "тип почвы";

    public SoilTypeExtractor() {
        super(Specification::findSoilType, PROPERTY_NAME);
    }

}