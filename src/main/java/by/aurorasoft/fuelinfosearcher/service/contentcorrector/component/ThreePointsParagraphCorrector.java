package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class ThreePointsParagraphCorrector extends AbstractContentParagraphCorrector {
    private static final String REPLACED_REGEX = "…";
    private static final String REPLACEMENT = "...";

    public ThreePointsParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }
}
