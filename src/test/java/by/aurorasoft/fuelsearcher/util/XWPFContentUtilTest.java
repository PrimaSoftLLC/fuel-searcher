package by.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesConsideringOnlyLettersAndDigits;
import static org.junit.Assert.assertEquals;

public final class XWPFContentUtilTest {

    @Test
    public void duplicatesShouldBeRemovedConsideringOnlyLettersAndDigits() {
        final Stream<String> givenSource = Stream.of(
                "CASE II Steiger 550",
                "CASE II     Ste-ige:r 550",
                "CASE II Steger 550",
                "CAS--E II Steiger 550",
                "C--ASE II Steiger 55"
        );

        final Stream<String> actual = removeDuplicatesConsideringOnlyLettersAndDigits(givenSource);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of(
                "CASE II Steiger 550",
                "CASE II Steger 550",
                "C--ASE II Steiger 55"
        );
        assertEquals(expectedAsList, actualAsList);
    }

//    @Test
//    public void stringShouldBeEqualIgnoringWhitespacesAndCases() {
//        final String firstGivenString = "   co\u00A0nT  E N\u00A0t \u00A0   \u00A0";
//        final String secondGivenString = "CO   Nte \u00A0\u00A0nT     ";
//
//        final boolean actual = areEqualConsideringOnlyLettersAndDigits(firstGivenString, secondGivenString);
//        assertTrue(actual);
//    }
//
//    @Test
//    public void stringShouldNotBeEqualIgnoringWhitespacesAndCases() {
//        final String firstGivenString = "   co\u00A0nT  E NN\u00A0t \u00A0   \u00A0";
//        final String secondGivenString = "CO   Nte \u00A0\u00A0nT     ";
//
//        final boolean actual = areEqualConsideringOnlyLettersAndDigits(firstGivenString, secondGivenString);
//        assertFalse(actual);
//    }
}
