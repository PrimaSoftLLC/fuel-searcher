package by.aurorasoft.fuelsearcher.service.factory.documentfactory.corrector.paragraphcorrector;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class NewLineParagraphCorrector extends ParagraphCorrector {
    private static final String REPLACED_REGEX = "\n+";
    private static final String REPLACEMENT = " ";

    public NewLineParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }

}
