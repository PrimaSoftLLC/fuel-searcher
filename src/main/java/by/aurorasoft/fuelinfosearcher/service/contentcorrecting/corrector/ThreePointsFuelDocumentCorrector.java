package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import org.springframework.stereotype.Component;

@Component
public final class ThreePointsFuelDocumentCorrector extends AbstractFuelDocumentContentCorrector {
    private static final String REPLACED_REGEX = "…";
    private static final String REPLACEMENT = "...";

    public ThreePointsFuelDocumentCorrector(final AbstractFuelDocumentContentCorrector nextCorrector) {
        super(REPLACED_REGEX, nextCorrector);
    }

    @Override
    protected String createReplacement(final String existing) {
        return REPLACEMENT;
    }
}
