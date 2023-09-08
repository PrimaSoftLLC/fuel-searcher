package by.aurorasoft.fuelsearcher.service.documentfactory.corrector.paragraphcorrector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class NewLineParagraphCorrectorTest {
    private final NewLineParagraphCorrector corrector = new NewLineParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = " ";
        assertEquals(expected, actual);
    }
}
