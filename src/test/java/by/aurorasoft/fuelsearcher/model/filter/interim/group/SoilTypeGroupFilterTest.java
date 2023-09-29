package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class SoilTypeGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final SoilTypeGroupFilter givenFilter = new SoilTypeGroupFilter(null, MIN_VALUE);

        final String actual = givenFilter.getGroupValueRegex();
        final String expected = "((Минеральные)|(Торфяные)|(Легкие)|(Средние)|(Тяжелые)) почвы";
        assertEquals(expected, actual);
    }
}
