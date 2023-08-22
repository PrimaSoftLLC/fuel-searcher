package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class QuoteParagraphCorrectorTest {
    private final QuoteParagraphCorrector corrector = new QuoteParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = corrector.createReplacement(null);
        final String expected = "\"";
        assertEquals(expected, actual);
    }
}
