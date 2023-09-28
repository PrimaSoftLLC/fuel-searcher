package by.aurorasoft.fuelsearcher.service.documentfactory.corrector.paragraphcorrector;

import by.aurorasoft.fuelsearcher.service.factory.documentfactory.corrector.paragraphcorrector.DashParagraphCorrector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class DashParagraphCorrectorTest {
    private final DashParagraphCorrector corrector = new DashParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = "-";
        assertEquals(expected, actual);
    }

}
