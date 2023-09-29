package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class ProcessingDepthGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final ProcessingDepthGroupFilter givenFilter = new ProcessingDepthGroupFilter(
                null, MIN_VALUE
        );

        final String actual = givenFilter.getGroupValueRegex();
        final String expected = "Глубина обработки \\d+...\\d+ см";
        assertEquals(expected, actual);
    }

}
