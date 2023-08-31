package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CargoClassExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "cargoClass";

    public CargoClassExtractor() {
        super(Specification::findCargoClass, PROPERTY_NAME);
    }
}
