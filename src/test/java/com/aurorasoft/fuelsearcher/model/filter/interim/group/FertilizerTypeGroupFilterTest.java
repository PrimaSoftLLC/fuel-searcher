package com.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class FertilizerTypeGroupFilterTest {
    private final FertilizerTypeGroupFilter filter = new FertilizerTypeGroupFilter(null, MIN_VALUE);

    @Test
    public void groupValuesShouldMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Гранулированные удобрения",
                "Кристаллические удобрения",
                "Пылевидные удобрения"
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
                " удобрения",
                "Какие-то удобрения",
                "Пылевидые удобрения"
        );

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }

}
