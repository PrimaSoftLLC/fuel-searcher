package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class ProcessingDepthGroupFilterTest {
    private final ProcessingDepthGroupFilter filter = new ProcessingDepthGroupFilter(
            null,
            MIN_VALUE
    );

    @Test
    public void groupValuesShouldMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Глубина обработки 1...2 см",
                "Глубина обработки 10...5 см",
                "Глубина обработки 0...99004343 см"
        );

        final boolean groupValuesMatchRegex = givenGroupValues
                .stream()
                .allMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesMatchRegex);
    }

    @Test
    public void groupValuesShouldNotMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Глубина обработки -1...2 см",
                "Глубина обработки 10..5 см",
                "Глубина обработки 0... см"
        );

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }
}
