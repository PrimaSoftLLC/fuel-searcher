package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class ProcessingDepthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "глубина обработки";

    public ProcessingDepthExtractor() {
        super(FuelSpecification::findProcessingDepth, PROPERTY_NAME);
    }

}
