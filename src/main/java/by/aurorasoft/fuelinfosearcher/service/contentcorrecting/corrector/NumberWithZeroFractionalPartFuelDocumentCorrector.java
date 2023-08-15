package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NumberWithZeroFractionalPartFuelDocumentCorrector extends AbstractContentFuelDocumentCorrector {
    private static final String REPLACED_REGEX = "(\\d+),0+";
    private static final int GROUP_NUMBER_NUMBER_WITHOUT_FRACTIONAL_PART = 1;

    public NumberWithZeroFractionalPartFuelDocumentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return matchResult.group(GROUP_NUMBER_NUMBER_WITHOUT_FRACTIONAL_PART);
    }

}
