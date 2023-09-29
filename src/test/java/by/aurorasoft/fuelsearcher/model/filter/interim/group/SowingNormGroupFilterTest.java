package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class SowingNormGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final SowingNormGroupFilter givenFilter = new SowingNormGroupFilter(null, MIN_VALUE);

        final String actual = givenFilter.getGroupValueRegex();
        final String expected = "Норма высева (семян )?\\d+(-\\d+)? кг/га";
        assertEquals(expected, actual);
    }

}
