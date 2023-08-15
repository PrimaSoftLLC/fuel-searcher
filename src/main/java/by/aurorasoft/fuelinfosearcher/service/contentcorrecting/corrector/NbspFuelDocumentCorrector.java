package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NbspFuelDocumentCorrector extends AbstractContentFuelDocumentCorrector {
    private static final String REPLACED_REGEX = "\u00A0+";
    private static final String REPLACEMENT = " ";

    public NbspFuelDocumentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
