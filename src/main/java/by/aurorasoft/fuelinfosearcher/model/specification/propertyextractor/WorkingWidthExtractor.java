package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class WorkingWidthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "ширина захвата";

    public WorkingWidthExtractor() {
        super(Specification::findWorkingWidth, PROPERTY_NAME);
    }

}
