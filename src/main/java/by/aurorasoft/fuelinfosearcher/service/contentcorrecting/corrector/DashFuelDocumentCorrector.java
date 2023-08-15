package by.aurorasoft.fuelinfosearcher.service.contentcorrecting.corrector;

import org.springframework.stereotype.Component;

@Component
public final class DashFuelDocumentCorrector extends AbstractContentFuelDocumentCorrector {
    private static final String REPLACED_REGEX = "…";
    private static final String REPLACEMENT = "...";

    public DashFuelDocumentCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final String existing) {
        return REPLACEMENT;
    }

}
