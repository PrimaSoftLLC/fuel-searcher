package by.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import static by.aurorasoft.fuelsearcher.util.XWPFContentComparingUtil.areEqualIgnoringWhitespacesAndCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class XWPFContentComparingUtilTest {

    @Test
    public void stringShouldBeEqualIgnoringWhitespacesAndCases() {
        final String firstGivenString = "   co\u00A0nT  E N\u00A0t \u00A0   \u00A0";
        final String secondGivenString = "CO   Nte \u00A0\u00A0nT     ";

        final boolean actual = areEqualIgnoringWhitespacesAndCase(firstGivenString, secondGivenString);
        assertTrue(actual);
    }

    @Test
    public void stringShouldNotBeEqualIgnoringWhitespacesAndCases() {
        final String firstGivenString = "   co\u00A0nT  E NN\u00A0t \u00A0   \u00A0";
        final String secondGivenString = "CO   Nte \u00A0\u00A0nT     ";

        final boolean actual = areEqualIgnoringWhitespacesAndCase(firstGivenString, secondGivenString);
        assertFalse(actual);
    }
}
