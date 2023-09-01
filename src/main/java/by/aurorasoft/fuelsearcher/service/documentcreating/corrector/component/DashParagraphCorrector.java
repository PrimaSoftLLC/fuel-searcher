package by.aurorasoft.fuelsearcher.service.documentcreating.corrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class DashParagraphCorrector extends ContentParagraphCorrector {
    private static final String REPLACED_REGEX = "–";
    private static final String REPLACEMENT = "-";

    public DashParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
