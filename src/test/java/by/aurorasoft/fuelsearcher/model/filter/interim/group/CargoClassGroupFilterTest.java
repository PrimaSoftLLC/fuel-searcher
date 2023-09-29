package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class CargoClassGroupFilterTest {

    @Test
    public void groupValuesShouldMatchRegex() {
        final CargoClassGroupFilter givenFilter = new CargoClassGroupFilter(null, MIN_VALUE);
        final Set<String> givenGroupValues = Set.of(
                "Грузы I класса",
                "Грузы II класса",
                "Грузы III класса",
                "Грузы IV класса"
        );

        final String groupValueRegex = givenFilter.getGroupValueRegex();

        final boolean groupValuesMatchRegex = givenGroupValues
                .stream()
                .allMatch(value -> value.matches(groupValueRegex));
        assertTrue(groupValuesMatchRegex);
    }

    @Test
    public void groupValuesShouldNotMatchRegex() {
        final CargoClassGroupFilter givenFilter = new CargoClassGroupFilter(null, MIN_VALUE);
        final Set<String> givenGroupValues = Set.of(
                "Грузы 1 класса",
                "Грузы класса",
                "Грузы g класса",
                "Грузы V класса"
        );

        final String groupValueRegex = givenFilter.getGroupValueRegex();

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(groupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }

}
