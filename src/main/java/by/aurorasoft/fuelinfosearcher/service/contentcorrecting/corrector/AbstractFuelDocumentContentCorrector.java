package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public abstract class AbstractFuelDocumentContentCorrector {
    private final Pattern patternReplacedRegex;
    private final AbstractFuelDocumentContentCorrector nextCorrector;

    public AbstractFuelDocumentContentCorrector(final String replacedRegex,
                                                final AbstractFuelDocumentContentCorrector nextCorrector) {
        this.patternReplacedRegex = compile(replacedRegex);
        this.nextCorrector = nextCorrector;
    }

    public final String correct(final StringBuilder contentBuilder) {
        this.replaceMatchesByReplacements(contentBuilder);
        return this.nextCorrector.correct(contentBuilder);
    }

    protected abstract String createReplacement(final String existing);

    private void replaceMatchesByReplacements(final StringBuilder contentBuilder) {
        this.patternReplacedRegex.matcher(contentBuilder)
                .results()
                .forEach(matchResult -> this.replaceMatchByReplacement(matchResult, contentBuilder));
    }

    private void replaceMatchByReplacement(final MatchResult matchResult, final StringBuilder contentBuilder) {
        final String existing = matchResult.group();
        final String replacement = this.createReplacement(existing);
        final int startMatchIndex = matchResult.start();
        final int endMatchNextIndex = matchResult.end();
        contentBuilder.replace(startMatchIndex, endMatchNextIndex, replacement);
    }
}
