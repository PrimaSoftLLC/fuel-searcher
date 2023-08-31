package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CargoClassExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "класс груза";

    public CargoClassExtractor() {
        super(Specification::findCargoClass, PROPERTY_NAME);
    }
}
