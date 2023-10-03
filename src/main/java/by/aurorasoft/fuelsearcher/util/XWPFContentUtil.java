package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public final class XWPFContentUtil {
    private static final String ALL_SYMBOLS_EXCEPT_LETTERS_AND_DIGITS_REGEX = "[^а-яА-Яa-zA-Z\\dIV]+";
    private static final String EMPTY_STRING = "";

    public static Stream<String> removeDuplicatesConsideringOnlyLettersAndDigits(final Stream<String> source) {
        final Set<String> accumulatedUniqueContentsOnlyWithDigitsAndLetters = new HashSet<>();
        return source.filter(
                sourceContent -> accumulatedUniqueContentsOnlyWithDigitsAndLetters.add(
                        removeAllSymbolsExceptLettersAndDigitsAndTransformToUpperCase(sourceContent)
                )
        );
    }

    public static boolean areEqualConsideringOnlyLettersAndDigits(final String first, final String second) {
        final String firstWithoutNotConsideringSymbols = removeAllExceptLettersAndDigits(first);
        final String secondWithoutNotConsideringSymbols = removeAllExceptLettersAndDigits(second);
        return firstWithoutNotConsideringSymbols.equalsIgnoreCase(secondWithoutNotConsideringSymbols);
    }

    private static String removeAllSymbolsExceptLettersAndDigitsAndTransformToUpperCase(final String source) {
        final String sourceWithOnlyLettersAndDigits = removeAllExceptLettersAndDigits(source);
        return sourceWithOnlyLettersAndDigits.toUpperCase();
    }

    private static String removeAllExceptLettersAndDigits(final String source) {
        return source.replaceAll(ALL_SYMBOLS_EXCEPT_LETTERS_AND_DIGITS_REGEX, EMPTY_STRING);
    }

}
