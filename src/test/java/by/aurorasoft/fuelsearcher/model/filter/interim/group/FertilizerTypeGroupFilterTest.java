package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class FertilizerTypeGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final FertilizerTypeGroupFilter givenFilter = new FertilizerTypeGroupFilter(null, MIN_VALUE);

        final String actual = givenFilter.getGroupValueRegex();
        final String expected = "((Гранулированные)|(Кристаллические)|(Пылевидные)) удобрения";
        assertEquals(expected, actual);
    }

}
