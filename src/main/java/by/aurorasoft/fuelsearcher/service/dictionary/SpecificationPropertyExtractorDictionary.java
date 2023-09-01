package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SpecificationPropertyExtractorDictionary extends Dictionary<SpecificationPropertyExtractor> {

    public SpecificationPropertyExtractorDictionary(final List<SpecificationPropertyExtractor> extractors) {
        super(extractors);
    }

}
