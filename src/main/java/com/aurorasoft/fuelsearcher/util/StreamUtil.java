package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.util.Iterator;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Stream.empty;
import static java.util.stream.StreamSupport.stream;

@UtilityClass
public final class StreamUtil {

    public static <T> Stream<T> asStream(final Iterator<T> iterator) {
        return asStream(iterator, false);
    }

    public static <T> Stream<T> asStream(final Iterator<T> iterator, final boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return stream(iterable.spliterator(), parallel);
    }

    @SafeVarargs
    public static <T> Stream<T> concat(final Stream<? extends T>... streams) {
        return streams != null
                ? Stream.of(streams).flatMap(identity())
                : empty();
    }

}
