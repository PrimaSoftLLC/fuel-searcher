package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class NbspParagraphCorrectorTest {
    private final NbspParagraphCorrector givenCorrector = new NbspParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = givenCorrector.createReplacement(null);
        final String expected = " ";
        assertEquals(expected, actual);
    }
}
