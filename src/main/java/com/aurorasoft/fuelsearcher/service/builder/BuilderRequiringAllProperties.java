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
            throw new IllegalStateException();
        }
    }
}
