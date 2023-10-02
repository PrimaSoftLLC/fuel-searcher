package by.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.MatchResult;

import static java.util.Optional.empty;

@Component
public final class RealNumberParagraphCorrector extends ParagraphCorrector {
    private static final char ZERO_CHARACTER = '0';
    private static final char POINT_CHARACTER = '.';

    private static final String REPLACED_REGEX = "(\\d+),(\\d+)";
    private static final int GROUP_NUMBER_INTEGER_PART = 1;
    private static final int GROUP_NUMBER_FRACTIONAL_PART = 2;

    public RealNumberParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        final String integerPart = extractIntegerPart(matchResult);
        return extractReducedFractionalPart(matchResult)
                .map(fractionalPart -> integerPart + POINT_CHARACTER + fractionalPart)
                .orElse(integerPart);
    }

    private static String extractIntegerPart(final MatchResult matchResult) {
        return matchResult.group(GROUP_NUMBER_INTEGER_PART);
    }

    private static Optional<String> extractReducedFractionalPart(final MatchResult matchResult) {
        final String fractionalPart = matchResult.group(GROUP_NUMBER_FRACTIONAL_PART);
        final String reducedFractionalPart = reduceFractionalPart(fractionalPart);
        return !reducedFractionalPart.isEmpty() ? Optional.of(reducedFractionalPart) : empty();
    }

    private static String reduceFractionalPart(final String source) {
        final StringBuilder fractionalPartBuilder = new StringBuilder(source);
        while (isReducibleFractionalPart(fractionalPartBuilder)) {
            removeLastCharacter(fractionalPartBuilder);
        }
        return fractionalPartBuilder.toString();
    }

    private static boolean isReducibleFractionalPart(final StringBuilder contentBuilder) {
        return !contentBuilder.isEmpty() && isLastCharacterZero(contentBuilder);
    }

    private static boolean isLastCharacterZero(final StringBuilder contentBuilder) {
        final int indexLastCharacter = findLastCharacterIndex(contentBuilder);
        final char lastCharacter = contentBuilder.charAt(indexLastCharacter);
        return isZero(lastCharacter);
    }

    private static boolean isZero(final char character) {
        return character == ZERO_CHARACTER;
    }

    private static void removeLastCharacter(final StringBuilder contentBuilder) {
        final int indexLastCharacter = findLastCharacterIndex(contentBuilder);
        contentBuilder.deleteCharAt(indexLastCharacter);
    }

    private static int findLastCharacterIndex(final StringBuilder contentBuilder) {
        return contentBuilder.length() - 1;
    }
}
