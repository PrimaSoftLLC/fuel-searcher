package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class RowWidthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "ширина междурядий";

    public RowWidthExtractor() {
        super(Specification::findRowWidth, PROPERTY_NAME);
    }

}
