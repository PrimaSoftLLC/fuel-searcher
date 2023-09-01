package by.aurorasoft.fuelsearcher.service.documentfactory.corrector.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class NbspParagraphCorrectorTest {
    private final NbspParagraphCorrector corrector = new NbspParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = corrector.createReplacement(null);
        final String expected = " ";
        assertEquals(expected, actual);
    }
}
