package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import java.util.Set;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertTrue;

public final class SowingNormGroupFilterTest {
    private final SowingNormGroupFilter filter = new SowingNormGroupFilter(null, MIN_VALUE);

    @Test
    public void groupValuesShouldMatchRegex() {
        final String givenGroupValueRegex = this.filter.getGroupValueRegex();
        final Set<String> givenGroupValues = Set.of(
                "Норма высева 3-5 кг/га",
                "Норма высева семян 3-55 кг/га",
                "Норма высева семян 3 кг/га",
                "Норма высева 1 кг/га"
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
                "Норма высева -1-5 кг/га",
                "Норма высева семян 3-55 кгга",
                "Нома высева семян 3 кг/га",
                "Норма высева кг/га"
        );

        final boolean groupValuesNotMatchRegex = givenGroupValues
                .stream()
                .noneMatch(value -> value.matches(givenGroupValueRegex));
        assertTrue(groupValuesNotMatchRegex);
    }
}
