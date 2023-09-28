package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TODO: refactor tests
@RequiredArgsConstructor
public final class SpecificationValidator implements Translatable {
    private final String tableName;
    private final List<SpecificationPropertyExtractor> requiredPropertyExtractors;

    @Override
    public String findAlias() {
        return this.tableName;
    }

    public SpecificationValidatingResult validate(final FuelSpecification specification) {
        this.validateTableName(specification);
        return this.createValidatingResult(specification);
    }

    private void validateTableName(final FuelSpecification specification) {
        if (!this.isValidTableName(specification)) {
            throw new IllegalArgumentException(
                    "Table's name in given specification isn't valid. Specification: %s".formatted(specification)
            );
        }
    }

    private boolean isValidTableName(final FuelSpecification specification) {
        return specification.findTableName()
                .map(tableName -> Objects.equals(tableName, this.tableName))
                .orElse(false);
    }

    private SpecificationValidatingResult createValidatingResult(final FuelSpecification specification) {
        final List<SpecificationPropertyExtractor> failedPropertyExtractors = this.findFailedPropertyExtractors(
                specification
        );
        return new SpecificationValidatingResult(failedPropertyExtractors);
    }

    private List<SpecificationPropertyExtractor> findFailedPropertyExtractors(final FuelSpecification specification) {
        return this.requiredPropertyExtractors.stream()
                .filter(propertyExtractor -> isFailedPropertyExtractor(propertyExtractor, specification))
                .toList();
    }

    private static boolean isFailedPropertyExtractor(final SpecificationPropertyExtractor extractor,
                                                     final FuelSpecification specification) {
        final Optional<String> optionalProperty = extractor.find(specification);
        return optionalProperty.isEmpty();
    }
}
