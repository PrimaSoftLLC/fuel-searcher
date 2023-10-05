package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public final class XWPFContentUtil {
    private static final String ALL_EXCEPT_LETTERS_AND_DIGITS_REGEX = "[^а-яА-Яa-zA-Z\\dIV]+";
    private static final String EMPTY_STRING = "";

    public static Stream<String> removeDuplicatesConsideringOnlyLettersAndDigits(final Stream<String> source) {
        final Set<String> accumulatedContentsOnlyWithDigitsAndLettersInUpperCase = new HashSet<>();
        return source.filter(
                content -> accumulatedContentsOnlyWithDigitsAndLettersInUpperCase.add(
                        transformToUppercaseOnlyWithLettersAndDigits(content)
                )
        );
    }

    public static boolean areEqualConsideringOnlyLettersAndDigits(final String first, final String second) {
        final String firstOnlyWithLettersAndDigits = transformToUppercaseOnlyWithLettersAndDigits(first);
        final String secondOnlyWithLettersAndDigits = transformToUppercaseOnlyWithLettersAndDigits(second);
        return Objects.equals(firstOnlyWithLettersAndDigits, secondOnlyWithLettersAndDigits);
    }

    private static String transformToUppercaseOnlyWithLettersAndDigits(final String source) {
        final String sourceWithOnlyLettersAndDigits = removeAllExceptLettersAndDigits(source);
        return sourceWithOnlyLettersAndDigits.toUpperCase();
    }

    private static String removeAllExceptLettersAndDigits(final String source) {
        return source.replaceAll(ALL_EXCEPT_LETTERS_AND_DIGITS_REGEX, EMPTY_STRING);
    }

}
