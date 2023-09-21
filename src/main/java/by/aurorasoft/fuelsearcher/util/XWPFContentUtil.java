package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public final class XWPFContentUtil {
    private static final String NBSP_OR_SPACE_SYMBOLS_REGEX = "[\\p{Z} ]+";
    private static final String EMPTY_STRING = "";

    //TODO: test
    public static String[] removeDuplicatesIgnoringWhitespacesAndCase(final Stream<String> source) {
        final Set<String> accumulatedContentsWithoutWhitespacesInUpperCase = new HashSet<>();
        return source.filter(
                sourceContent -> accumulatedContentsWithoutWhitespacesInUpperCase.add(
                        removeWhitespacesAndTransformToUpperCase(sourceContent)
                )
        ).toArray(String[]::new);
    }

    public static boolean areEqualIgnoringWhitespacesAndCase(final String first, final String second) {
        final String firstWithoutSpaces = removeAllWhitespaces(first);
        final String secondWithoutSpaces = removeAllWhitespaces(second);
        return firstWithoutSpaces.equalsIgnoreCase(secondWithoutSpaces);
    }

    private static String removeWhitespacesAndTransformToUpperCase(final String source) {
        final String sourceWithoutWhitespaces = removeAllWhitespaces(source);
        return sourceWithoutWhitespaces.toUpperCase();
    }

    private static String removeAllWhitespaces(final String source) {
        return source.replaceAll(NBSP_OR_SPACE_SYMBOLS_REGEX, EMPTY_STRING);
    }

}
