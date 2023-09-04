package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class CorpusCountExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "количество корпусов";

    public CorpusCountExtractor() {
        super(FuelSpecification::findCorpusCount, PROPERTY_NAME);
    }

}
