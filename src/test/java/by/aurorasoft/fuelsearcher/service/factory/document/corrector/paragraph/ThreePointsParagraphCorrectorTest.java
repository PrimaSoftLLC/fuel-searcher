package by.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.junit.Test;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findStaticFieldValue;
import static org.junit.Assert.*;

public final class ThreePointsParagraphCorrectorTest {
    private static final String FIELD_NAME_REPLACED_REGEX = "REPLACED_REGEX";

    private final ThreePointsParagraphCorrector corrector = new ThreePointsParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = "...";
        assertEquals(expected, actual);
    }

    @Test
    public void contentShouldMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final String givenContent = "…";

        final boolean actual = givenContent.matches(givenReplacedRegex);
        assertTrue(actual);
    }

    @Test
    public void contentShouldNotMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final String givenContent = "...";

        final boolean actual = givenContent.matches(givenReplacedRegex);
        assertFalse(actual);
    }

    private static String findReplacedRegex() {
        return findStaticFieldValue(
                ThreePointsParagraphCorrector.class,
                FIELD_NAME_REPLACED_REGEX,
                String.class
        );
    }
}
