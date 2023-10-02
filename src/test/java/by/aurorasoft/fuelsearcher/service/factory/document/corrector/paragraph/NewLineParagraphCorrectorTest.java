package by.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.junit.Test;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findStaticFieldValue;
import static org.junit.Assert.*;

public final class NewLineParagraphCorrectorTest {
    private static final String FIELD_NAME_REPLACED_REGEX = "REPLACED_REGEX";

    private final NewLineParagraphCorrector corrector = new NewLineParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = " ";
        assertEquals(expected, actual);
    }

    @Test
    public void contentShouldMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final String givenContent = "\n\n\n\n\n";

        final boolean actual = givenContent.matches(givenReplacedRegex);
        assertTrue(actual);
    }

    @Test
    public void contentShouldNotMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final String givenContent = "";

        final boolean actual = givenContent.matches(givenReplacedRegex);
        assertFalse(actual);
    }

    private static String findReplacedRegex() {
        return findStaticFieldValue(
                NewLineParagraphCorrector.class,
                FIELD_NAME_REPLACED_REGEX,
                String.class
        );
    }
}
