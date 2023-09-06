package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import lombok.Value;

import java.util.List;

import static lombok.AccessLevel.NONE;

@Value
public class SpecificationValidatingResult {

    @Getter(value = NONE)
    List<SpecificationPropertyExtractor> failedPropertyExtractors;

    public boolean isValid() {
        return this.failedPropertyExtractors.isEmpty();
    }

    public List<String> findFailedPropertyNames() {
        return this.failedPropertyExtractors.stream()
                .map(SpecificationPropertyExtractor::getPropertyName)
                .toList();
    }
}
