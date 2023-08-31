package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CorpusCountExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "количество корпусов";

    public CorpusCountExtractor() {
        super(Specification::findCorpusCount, PROPERTY_NAME);
    }

}
