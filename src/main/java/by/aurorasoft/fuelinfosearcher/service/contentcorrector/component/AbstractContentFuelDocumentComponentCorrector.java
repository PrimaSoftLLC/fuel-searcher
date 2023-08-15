package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public abstract class AbstractContentFuelDocumentComponentCorrector {
    private final Pattern patternReplacedRegex;

    public AbstractContentFuelDocumentComponentCorrector(final String replacedRegex) {
        this.patternReplacedRegex = compile(replacedRegex);
    }

    public final String correct(final String content) {
        return this.replaceMatchesByReplacements(content);
    }

    protected abstract String createReplacement(final MatchResult matchResult);

    private String replaceMatchesByReplacements(final String content) {
        final Matcher matcher = this.patternReplacedRegex.matcher(content);
        return matcher.replaceAll(this::createReplacement);
    }
}
