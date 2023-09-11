package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class XWPFContentComparingUtil {
    private static final String NBSP_OR_SPACE_SYMBOLS_REGEX = "[\\p{Z} ]+";
    private static final String EMPTY_STRING = "";

    public static boolean areEqualIgnoringWhitespacesAndCase(final String first, final String second) {
        final String firstWithoutSpaces = removeAllWhitespaces(first);
        final String secondWithoutSpaces = removeAllWhitespaces(second);
        return firstWithoutSpaces.equalsIgnoreCase(secondWithoutSpaces);
    }

    private static String removeAllWhitespaces(final String source) {
        return source.replaceAll(NBSP_OR_SPACE_SYMBOLS_REGEX, EMPTY_STRING);
    }

}
