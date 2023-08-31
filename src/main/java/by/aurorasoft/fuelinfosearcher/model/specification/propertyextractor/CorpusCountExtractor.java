package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CorpusCountExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "corpusCount";

    public CorpusCountExtractor() {
        super(Specification::findCorpusCount, PROPERTY_NAME);
    }

}
