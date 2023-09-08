package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class RoadGroupGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final RoadGroupGroupFilter givenFilter = new RoadGroupGroupFilter(null, MIN_VALUE);

        final String actual = givenFilter.findGroupValueRegex();
        final String expected = "((Первая)|(Вторая)|(Третья)) группа дорог";
        assertEquals(expected, actual);
    }

}
