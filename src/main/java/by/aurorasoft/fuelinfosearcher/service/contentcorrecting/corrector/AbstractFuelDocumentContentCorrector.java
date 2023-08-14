package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public abstract class AbstractFuelDocumentContentCorrector {
    private final Pattern patternReplacedRegex;

    public AbstractFuelDocumentContentCorrector(final String replacedRegex) {
        this.patternReplacedRegex = compile(replacedRegex);
    }

    public final String correct(final String content) {

        return content.(this.replacedRegex, )
    }

    protected abstract String createReplacement(final String existing);
}
