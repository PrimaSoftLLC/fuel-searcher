package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;

public final class SpecificResistanceGroupFilterTest {

    @Test
    public void groupValueRegexShouldBeFound() {
        final SpecificResistanceGroupFilter givenFilter = new SpecificResistanceGroupFilter(
                null, MIN_VALUE
        );

        final String actual = givenFilter.findGroupValueRegex();
        final String expected = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
        assertEquals(expected, actual);
    }
}
