package com.aurorasoft.fuelsearcher.service.dictionary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public abstract class Dictionary<V extends Translatable> {
    private final Map<String, V> valuesByAliases;

    public Dictionary(final List<V> values) {
        this.valuesByAliases = createValuesByAliases(values);
    }

    public final Optional<V> find(final String alias) {
        final V value = this.valuesByAliases.get(alias);
        return ofNullable(value);
    }

    private static <V extends Translatable> Map<String, V> createValuesByAliases(final List<V> values) {
        return values.stream()
                .collect(
                        toMap(
                                Translatable::findAlias,
                                identity(),
                                Dictionary::throwSeveralValuesForOneAliasException
                        )
                );
    }

    private static <V extends Translatable> V throwSeveralValuesForOneAliasException(final V first, final V second) {
        final String alias = first.findAlias();
        throw new SeveralValuesForOneAliasException(
                "There are several values for alias '%s'".formatted(alias)
        );
    }

    private static final class SeveralValuesForOneAliasException extends RuntimeException {

        @SuppressWarnings("unused")
        public SeveralValuesForOneAliasException() {

        }

        public SeveralValuesForOneAliasException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public SeveralValuesForOneAliasException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public SeveralValuesForOneAliasException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
