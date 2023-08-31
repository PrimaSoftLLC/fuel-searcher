package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class ProcessingDepthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "глубина обработки";

    public ProcessingDepthExtractor() {
        super(Specification::findProcessingDepth, PROPERTY_NAME);
    }

}
