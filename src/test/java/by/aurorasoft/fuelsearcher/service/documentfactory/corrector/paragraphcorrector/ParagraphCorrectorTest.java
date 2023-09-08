package by.aurorasoft.fuelsearcher.service.documentfactory.corrector.paragraphcorrector;

import by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.regex.MatchResult;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.replaceText;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class ParagraphCorrectorTest {

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void paragraphShouldBeCorrected() {
        try (final MockedStatic<XWPFParagraphUtil> mockedUtil = mockStatic(XWPFParagraphUtil.class)) {
            final String givenReplacedRegex = "existing";
            final String givenReplacement = "replacement";
            final TestParagraphCorrector givenCorrector = new TestParagraphCorrector(
                    givenReplacedRegex, givenReplacement
            );

            final String givenParagraphContent = "existing text textexisting text existingword";
            final XWPFParagraph givenParagraph = createParagraph(givenParagraphContent);

            givenCorrector.correct(givenParagraph);
            mockedUtil.verify(() -> replaceText(same(givenParagraph), this.stringArgumentCaptor.capture()));

            final String expected = "replacement text textreplacement text replacementword";
            final String actual = this.stringArgumentCaptor.getValue();
            Assert.assertEquals(expected, actual);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFParagraph createParagraph(final String content) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        when(paragraph.getText()).thenReturn(content);
        return paragraph;
    }

    private static final class TestParagraphCorrector extends ParagraphCorrector {
        private final String replacement;

        public TestParagraphCorrector(final String replacedRegex, final String replacement) {
            super(replacedRegex);
            this.replacement = replacement;
        }

        @Override
        protected String createReplacement(final MatchResult matchResult) {
            return this.replacement;
        }
    }

}
