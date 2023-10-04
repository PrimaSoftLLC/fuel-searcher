package com.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toSet;

@UtilityClass
public final class CollectionUtil {

    public static boolean isImmutableList(final List<?> research) {
        final List<?> tempImmutableList = unmodifiableList(new ArrayList<>());
        final Class<?> typeImmutableList = tempImmutableList.getClass();
        return typeImmutableList.isInstance(research);
    }

    public static <T> boolean areEqualIgnoringOrder(final T[] expected, final T[] actual) {
        final Set<T> expectedAsSet = mapToSet(expected);
        final Set<T> actualAsSet = mapToSet(actual);
        return Objects.equals(expectedAsSet, actualAsSet);
    }

    private static <T> Set<T> mapToSet(final T[] source) {
        return stream(source).collect(toSet());
    }
}
