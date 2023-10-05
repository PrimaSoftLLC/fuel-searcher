package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.areEqualConsideringOnlyLettersAndDigits;
import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesConsideringOnlyLettersAndDigits;
import static org.junit.Assert.*;

public final class XWPFContentUtilTest {

    @Test
    public void duplicatesShouldBeRemovedConsideringOnlyLettersAndDigits() {
        final Stream<String> givenSource = Stream.of(
                "CASE II Steiger 550",
                "CASE II     ste-ige:r 5\u00A050",
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

    @Test
    public void contentsShouldBeEqualConsideringOnlyLettersAndDigits() {
        final String firstGivenContent = "CASE II Steiger 550";
        final String secondGivenContent = "CASE II     ste-ige:r 5\u00A050";

        final boolean actual = areEqualConsideringOnlyLettersAndDigits(firstGivenContent, secondGivenContent);
        assertTrue(actual);
    }

    @Test
    public void contentsShouldNotBeEqualConsideringOnlyLettersAndDigits() {
        final String firstGivenContent = "CASE II Steiger 550";
        final String secondGivenContent = "CAS II     Ste-ige:r 5\u00A050";

        final boolean actual = areEqualConsideringOnlyLettersAndDigits(firstGivenContent, secondGivenContent);
        assertFalse(actual);
    }
}
