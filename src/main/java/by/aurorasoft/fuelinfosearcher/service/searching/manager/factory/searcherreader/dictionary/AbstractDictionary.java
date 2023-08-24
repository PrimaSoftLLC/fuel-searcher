package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public abstract class AbstractDictionary<V> {
    private final Map<String, V> valuesByKeys;

    public final Optional<V> find(final String key) {
        final V value = this.valuesByKeys.get(key);
        return ofNullable(value);
    }
}
