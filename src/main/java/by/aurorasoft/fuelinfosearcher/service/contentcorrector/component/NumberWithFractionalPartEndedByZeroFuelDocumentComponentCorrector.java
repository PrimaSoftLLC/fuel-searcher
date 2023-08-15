package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NumberWithFractionalPartEndedByZeroFuelDocumentComponentCorrector extends AbstractContentFuelDocumentComponentCorrector {
    private static final String INTEGER_AND_FRACTIONAL_PART_DELIMITER = ",";
    private static final String REPLACED_REGEX = "(\\d+)" + INTEGER_AND_FRACTIONAL_PART_DELIMITER + "([0-9]+)?0+$";
    private static final int GROUP_NUMBER_INTEGER_PART = 1;
    private static final int GROUP_NUMBER_TRUNCATED_FRACTIONAL_PART = 2;

    public NumberWithFractionalPartEndedByZeroFuelDocumentComponentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        final String integerPart = matchResult.group(GROUP_NUMBER_INTEGER_PART);
        final String truncatedFractionalPart = matchResult.group(GROUP_NUMBER_TRUNCATED_FRACTIONAL_PART);
        return truncatedFractionalPart != null
                ? integerPart + INTEGER_AND_FRACTIONAL_PART_DELIMITER + truncatedFractionalPart
                : integerPart;
    }

}
