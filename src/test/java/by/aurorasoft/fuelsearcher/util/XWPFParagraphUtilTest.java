package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.isEmptyParagraph;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class XWPFParagraphUtilTest {

    @Test
    public void elementShouldBeEmptyParagraph() {
        final IBodyElement givenElement = createParagraph(" \\u00A0   \\u00A0   ");

        final boolean actual = isEmptyParagraph(givenElement);
        assertTrue(actual);
    }

    @Test
    public void elementShouldNotBeEmptyParagraphBecauseOfItIsNotEmpty() {
        final IBodyElement givenElement = createParagraph(" \\u00A0 text  \\u00A0   ");

        final boolean actual = isEmptyParagraph(givenElement);
        assertFalse(actual);
    }

    @Test
    public void elementShouldNotBeEmptyParagraphBecauseOfItIsNotParagraph() {
        final IBodyElement givenElement = mock(IBodyElement.class);

        final boolean actual = isEmptyParagraph(givenElement);
        assertFalse(actual);
    }

    private static XWPFParagraph createParagraph(final String text) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        when(paragraph.getText()).thenReturn(text);
        return paragraph;
    }
}
