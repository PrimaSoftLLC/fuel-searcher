package com.aurorasoft.fuelsearcher.service.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class RoadGroupGroupFilterTest {
    private final RoadGroupGroupFilter filter = new RoadGroupGroupFilter(null, MIN_VALUE);

    @Test
    public void groupValuesShouldMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Первая группа дорог",
                "Вторая группа дорог",
                "Третья группа дорог"
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
                "1 группа дорог",
                "Втора группа дорог",
                " группа дорог"
        );

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }
}
