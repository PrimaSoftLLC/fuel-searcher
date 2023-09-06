package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class SpecificationValidator implements Translatable {
    private final String tableName;
    private final List<SpecificationPropertyExtractor> requiredPropertyExtractors;

    public static SpecificationValidatorBuilder builder() {
        return new SpecificationValidatorBuilder();
    }

    @Override
    public String findAlias() {
        return this.tableName;
    }

    public boolean isValid(final FuelSpecification specification) {
        this.validateTableName(specification);
        return this.isSpecificationContainRequiredProperties(specification);
    }

    private void validateTableName(final FuelSpecification specification) {
        final boolean validTableName = this.isValidTableName(specification);
        if (!validTableName) {
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

    private boolean isSpecificationContainRequiredProperties(final FuelSpecification specification) {
        return this.requiredPropertyExtractors.stream()
                .allMatch(
                        propertyExtractor -> isSpecificationContainProperty(specification, propertyExtractor)
                );
    }

    private static boolean isSpecificationContainProperty(final FuelSpecification specification,
                                                          final SpecificationPropertyExtractor propertyExtractor) {
        final Optional<String> optionalProperty = propertyExtractor.findProperty(specification);
        return optionalProperty.isPresent();
    }

    public static final class SpecificationValidatorBuilder
            extends BuilderRequiringAllProperties<SpecificationValidator> {
        private String tableName;
        private List<SpecificationPropertyExtractor> requiredPropertyExtractors;

        public void tableName(final String tableName) {
            this.tableName = tableName;
        }

        public void requiredPropertyExtractor(final SpecificationPropertyExtractor extractor) {
            this.initializeRequiredPropertyExtractorsIfNecessary();
            this.requiredPropertyExtractors.add(extractor);
        }

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.tableName, this.requiredPropertyExtractors);
        }

        @Override
        protected SpecificationValidator buildAfterStateValidation() {
            final List<SpecificationPropertyExtractor> immutableExtractors = unmodifiableList(this.requiredPropertyExtractors);
            return new SpecificationValidator(this.tableName, immutableExtractors);
        }

        private void initializeRequiredPropertyExtractorsIfNecessary() {
            if (this.requiredPropertyExtractors == null) {
                this.requiredPropertyExtractors = new ArrayList<>();
            }
        }
    }
}