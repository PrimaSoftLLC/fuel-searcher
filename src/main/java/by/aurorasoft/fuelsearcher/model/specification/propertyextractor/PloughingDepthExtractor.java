package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class PloughingDepthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "глубина вспашки";

    public PloughingDepthExtractor() {
        super(Specification::findPloughingDepth, PROPERTY_NAME);
    }

}
