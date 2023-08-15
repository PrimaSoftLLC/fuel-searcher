package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class ThreePointsFuelDocumentCorrector extends AbstractContentFuelDocumentCorrector {
    private static final String REPLACED_REGEX = "…";
    private static final String REPLACEMENT = "...";

    public ThreePointsFuelDocumentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }
}
