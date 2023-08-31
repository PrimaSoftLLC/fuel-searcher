package by.aurorasoft.fuelinfosearcher.dictionary;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SpecificationPropertyExtractorDictionary extends Dictionary<SpecificationPropertyExtractor> {

    public SpecificationPropertyExtractorDictionary(final List<SpecificationPropertyExtractor> extractors) {
        super(extractors);
    }

}
