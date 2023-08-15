package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NumberWithFractionalPartEndedByZeroFuelDocumentComponentCorrector extends AbstractContentFuelDocumentComponentCorrector {
    private static final char ZERO_CHARACTER = '0';
    private static final char COMMA_CHARACTER = ',';
    private static final String REPLACED_REGEX = "\\d+,\\d*0";

    public NumberWithFractionalPartEndedByZeroFuelDocumentComponentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        final String content = matchResult.group();
//        if(content.equals("1,04")){
//            System.out.println();
//        }
        return removeLastZerosAndCommas(content);
    }

    private static String removeLastZerosAndCommas(final String content) {
        final StringBuilder contentBuilder = new StringBuilder(content);
        while (isLastCharacterZeroOrComma(contentBuilder)) {
            removeLastCharacter(contentBuilder);
        }
        return contentBuilder.toString();
    }

    private static boolean isLastCharacterZeroOrComma(final StringBuilder contentBuilder) {
        final int indexLastCharacter = findLastCharacterIndex(contentBuilder);
        final char lastCharacter = contentBuilder.charAt(indexLastCharacter);
        return lastCharacter == ZERO_CHARACTER || lastCharacter == COMMA_CHARACTER;
    }

    private static void removeLastCharacter(final StringBuilder contentBuilder) {
        final int indexLastCharacter = findLastCharacterIndex(contentBuilder);
        contentBuilder.deleteCharAt(indexLastCharacter);
    }

    private static int findLastCharacterIndex(final StringBuilder contentBuilder) {
        return contentBuilder.length() - 1;
    }
}
