package by.aurorasoft.fuelsearcher.service.documentcreating.contentcorrector.component;

import org.junit.Test;

import java.util.regex.MatchResult;

import static org.junit.Assert.assertEquals;

public final class AbstractContentParagraphCorrectorTest {

    @Test
    public void contentShouldBeCorrected() {
        final String givenReplacedRegex = "existing";
        final String givenReplacement = "replacement";
        final TestContentParagraphCorrector givenCorrector = new TestContentParagraphCorrector(
                givenReplacedRegex, givenReplacement
        );
        final String givenContent = "existing text textexisting text existingword";

        final String actual = givenCorrector.correct(givenContent);
        final String expected = "replacement text textreplacement text replacementword";
        assertEquals(expected, actual);
    }

    private static final class TestContentParagraphCorrector extends AbstractContentParagraphCorrector {
        private final String replacement;

        public TestContentParagraphCorrector(final String replacedRegex, final String replacement) {
            super(replacedRegex);
            this.replacement = replacement;
        }

        @Override
        protected String createReplacement(final MatchResult matchResult) {
            return this.replacement;
        }
    }

}
