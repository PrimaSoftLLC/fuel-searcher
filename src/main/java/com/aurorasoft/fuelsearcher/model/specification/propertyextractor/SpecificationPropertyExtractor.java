package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class SpecificationPropertyExtractor implements Translatable {
    private final Function<FuelSpecification, Optional<String>> propertyFounder;

    @Getter
    private final String propertyName;

    @Override
    public final String findAlias() {
        return this.propertyName;
    }

    public final Optional<String> find(final FuelSpecification specification) {
        return this.propertyFounder.apply(specification);
    }

    public final String extract(final FuelSpecification specification) {
        final Optional<String> optionalProperty = this.find(specification);
        return optionalProperty.orElseThrow(
                () -> new PropertyExtractingException(
                        "Specification property '%s' isn't defined".formatted(this.propertyName)
                )
        );
    }

    private static final class PropertyExtractingException extends RuntimeException {

        @SuppressWarnings("unused")
        public PropertyExtractingException() {

        }

        public PropertyExtractingException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public PropertyExtractingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public PropertyExtractingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
