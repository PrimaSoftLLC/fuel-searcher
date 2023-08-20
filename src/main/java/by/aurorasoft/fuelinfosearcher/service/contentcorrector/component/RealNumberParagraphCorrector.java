package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.MatchResult;

@Component
public final class RealNumberParagraphCorrector extends AbstractContentParagraphCorrector {
    private static final char ZERO_CHARACTER = '0';
    private static final char COMMA_CHARACTER = ',';
    private static final String REPLACED_REGEX = "\\d+,\\d+";

    public RealNumberParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        final String content = matchResult.group();
        return removeLastZerosAndCommas(content);
    }

    private static String removeLastZerosAndCommas(final String content) {
        final StringBuilder contentBuilder = new StringBuilder(content);
        removeLastZerosIfExist(contentBuilder);
        removeLastCommasIfExist(contentBuilder);
        return contentBuilder.toString();
    }

    private static void removeLastZerosIfExist(final StringBuilder contentBuilder) {
        removeLastCharactersIfMatch(
                contentBuilder,
                RealNumberParagraphCorrector::isZero
        );
    }

    private static void removeLastCommasIfExist(final StringBuilder contentBuilder) {
        removeLastCharactersIfMatch(
                contentBuilder,
                RealNumberParagraphCorrector::isLastCharacterComma
        );
    }

    private static void removeLastCharactersIfMatch(final StringBuilder contentBuilder,
                                                    final Predicate<Character> matchPredicate) {
        int indexLastCharacter = findLastCharacterIndex(contentBuilder);
        char lastCharacter = contentBuilder.charAt(indexLastCharacter);
        while (matchPredicate.test(lastCharacter)) {
            contentBuilder.deleteCharAt(indexLastCharacter);
            indexLastCharacter = findLastCharacterIndex(contentBuilder);
            lastCharacter = contentBuilder.charAt(indexLastCharacter);
        }
    }

    private static boolean isZero(final char character) {
        return character == ZERO_CHARACTER;
    }

    private static boolean isLastCharacterComma(final char character) {
        return character == COMMA_CHARACTER;
    }

    private static int findLastCharacterIndex(final StringBuilder contentBuilder) {
        return contentBuilder.length() - 1;
    }
}
