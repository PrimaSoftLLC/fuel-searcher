package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Collections.singletonList;

@RequiredArgsConstructor
public final class SpecificationValidatingResult {
    private final List<SpecificationPropertyExtractor> failedPropertyExtractors;

    public static SpecificationValidatingResult createNotValidValidatingResult(
            final SpecificationPropertyExtractor failedPropertyExtractor) {
        final List<SpecificationPropertyExtractor> failedPropertyExtractors = singletonList(failedPropertyExtractor);
        return new SpecificationValidatingResult(failedPropertyExtractors);
    }

    public boolean isValid() {
        return this.failedPropertyExtractors.isEmpty();
    }

    public List<String> findFailedPropertyNames() {
        return this.failedPropertyExtractors.stream()
                .map(SpecificationPropertyExtractor::getPropertyName)
                .toList();
    }
}
