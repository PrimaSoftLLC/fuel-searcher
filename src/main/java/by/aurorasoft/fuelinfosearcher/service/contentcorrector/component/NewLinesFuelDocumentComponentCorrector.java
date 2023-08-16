package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NewLinesFuelDocumentComponentCorrector extends AbstractContentFuelDocumentComponentCorrector {
    private static final String REPLACED_REGEX = "\n+";
    private static final String REPLACEMENT = " ";

    public NewLinesFuelDocumentComponentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
