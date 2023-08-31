package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class TractorExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "трактор";

    public TractorExtractor() {
        super(Specification::findTractor, PROPERTY_NAME);
    }

}