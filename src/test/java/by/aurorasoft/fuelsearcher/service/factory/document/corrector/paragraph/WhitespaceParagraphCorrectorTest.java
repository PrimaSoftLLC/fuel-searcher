package by.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.junit.Test;

import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findStaticFieldValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class WhitespaceParagraphCorrectorTest {
    private static final String FIELD_NAME_REPLACED_REGEX = "REPLACED_REGEX";

    private final WhitespaceParagraphCorrector corrector = new WhitespaceParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = " ";
        assertEquals(expected, actual);
    }

    @Test
    public void contentsShouldMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final Stream<String> givenContents = Stream.of("   \u00A0    \u00A0   ", " ", "\u00A0");

        final boolean actual = givenContents.allMatch(content -> content.matches(givenReplacedRegex));
        assertTrue(actual);
    }

    @Test
    public void contentShouldNotMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final Stream<String> givenContents = Stream.of("    b  ", "");

        final boolean actual = givenContents.noneMatch(content -> content.matches(givenReplacedRegex));
        assertTrue(actual);
    }

    private static String findReplacedRegex() {
        return findStaticFieldValue(
                WhitespaceParagraphCorrector.class,
                FIELD_NAME_REPLACED_REGEX,
                String.class
        );
    }
}
