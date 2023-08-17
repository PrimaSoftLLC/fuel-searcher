package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class DashParagraphCorrectorTest {
    private final DashParagraphCorrector givenCorrector = new DashParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.givenCorrector.createReplacement(null);
        final String expected = "-";
        assertEquals(expected, actual);
    }

}
