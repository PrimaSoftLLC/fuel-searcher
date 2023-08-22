package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class ThreePointsParagraphCorrectorTest {
    private final ThreePointsParagraphCorrector corrector = new ThreePointsParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = "...";
        assertEquals(expected, actual);
    }
}
