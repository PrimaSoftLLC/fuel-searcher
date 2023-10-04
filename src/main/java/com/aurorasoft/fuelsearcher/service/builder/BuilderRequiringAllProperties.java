package com.aurorasoft.fuelsearcher.service.builder;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class BuilderRequiringAllProperties<T> {

    public final T build() {
        this.validateState();
        return this.buildAfterStateValidation();
    }

    protected abstract Stream<Object> findProperties();

    protected abstract T buildAfterStateValidation();

    private void validateState() {
        final Stream<Object> properties = this.findProperties();
        final boolean validState = properties.noneMatch(Objects::isNull);
        if (!validState) {
            throw new NotDefinedPropertyException();
        }
    }

    private static final class NotDefinedPropertyException extends RuntimeException {

        public NotDefinedPropertyException() {

        }

        @SuppressWarnings("unused")
        public NotDefinedPropertyException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NotDefinedPropertyException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NotDefinedPropertyException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
