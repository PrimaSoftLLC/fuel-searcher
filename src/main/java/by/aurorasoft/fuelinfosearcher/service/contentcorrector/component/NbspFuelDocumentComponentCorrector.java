package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NbspFuelDocumentComponentCorrector extends AbstractContentFuelDocumentComponentCorrector {
    private static final String REPLACED_REGEX = "\u00A0+";
    private static final String REPLACEMENT = " ";

    public NbspFuelDocumentComponentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
