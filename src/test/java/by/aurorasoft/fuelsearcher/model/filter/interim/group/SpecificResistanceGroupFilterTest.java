package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class SpecificResistanceGroupFilterTest {
    private final SpecificResistanceGroupFilter filter = new SpecificResistanceGroupFilter(
            null,
            MIN_VALUE
    );

    @Test
    public void groupValuesShouldMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Удельное сопротивление плуга 3...10 кПа",
                "Удельное сопротивление 3...10 кПа"
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
                "Удельное сопротивление плуга -1...10 кПа",
                "Удельное сопротивление 3..10 кПа",
                "Удельное сопротивление плуга кПа",
                "Удельное сопротивление 3 кПа"
        );

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }
}
