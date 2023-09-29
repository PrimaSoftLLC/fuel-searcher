package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class CargoClassGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final CargoClassGroupFilter givenFilter = new CargoClassGroupFilter(null, MIN_VALUE);

        final String actual = givenFilter.getGroupValueRegex();
        final String expected = "Грузы (I|II|III|IV) класса";
        assertEquals(expected, actual);
    }

}
