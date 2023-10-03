package by.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.StreamUtil.asStream;
import static by.aurorasoft.fuelsearcher.util.StreamUtil.concat;
import static java.util.stream.Stream.empty;
import static org.junit.Assert.*;

public final class StreamUtilTest {

    @Test
    public void iteratorShouldBeConvertedToStream() {
        final Iterator<Integer> givenIterator = new IntegerIterator(1, 2, 3);

        final Stream<Integer> actual = asStream(givenIterator);
        assertFalse(actual.isParallel());

        final List<Integer> actualAsList = actual.toList();
        final List<Integer> expectedAsList = List.of(1, 2, 3);
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void iteratorShouldBeConvertedToParallelStream() {
        final Iterator<Integer> givenIterator = new IntegerIterator(1, 2, 3);

        final Stream<Integer> actual = asStream(givenIterator, true);
        assertTrue(actual.isParallel());

        final List<Integer> actualAsList = actual.toList();
        final List<Integer> expectedAsList = List.of(1, 2, 3);
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void streamsShouldBeConcatenated() {
        final Stream<Integer> firstGivenStream = empty();
        final Stream<Integer> secondGivenStream = Stream.of(1);
        final Stream<Integer> thirdGivenStream = Stream.of(2, 3);

        final Stream<Integer> actual = concat(
                firstGivenStream,
                null,
                secondGivenStream,
                thirdGivenStream,
                null
        );
        final List<Integer> actualAsList = actual.toList();
        final List<Integer> expectedAsList = List.of(1, 2, 3);
        assertEquals(expectedAsList, actualAsList);
    }

    private static final class IntegerIterator implements Iterator<Integer> {
        private final Integer[] numbers;
        private int nextNumberIndex;

        public IntegerIterator(final Integer... numbers) {
            this.numbers = numbers;
        }

        @Override
        public boolean hasNext() {
            return this.nextNumberIndex < this.numbers.length;
        }

        @Override
        public Integer next() {
            return this.numbers[this.nextNumberIndex++];
        }
    }
}
