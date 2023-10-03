package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.function.Function.identity;
import static java.util.stream.Stream.empty;

@UtilityClass
public final class StreamUtil {

    public static <T> Stream<T> asStream(final Iterator<T> iterator) {
        return asStream(iterator, false);
    }

    public static <T> Stream<T> asStream(final Iterator<T> iterator, final boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    @SafeVarargs
    public static <T> Stream<T> concat(final Stream<? extends T>... streams) {
        return streams != null
                ? Stream.of(streams).flatMap(identity())
                : empty();
    }

}
