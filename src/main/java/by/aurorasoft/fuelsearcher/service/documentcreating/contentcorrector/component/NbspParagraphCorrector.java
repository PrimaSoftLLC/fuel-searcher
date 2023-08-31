package by.aurorasoft.fuelsearcher.service.documentcreating.contentcorrector.component;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NbspParagraphCorrector extends AbstractContentParagraphCorrector {
    private static final String REPLACED_REGEX = "\u00A0+";
    private static final String REPLACEMENT = " ";

    public NbspParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
