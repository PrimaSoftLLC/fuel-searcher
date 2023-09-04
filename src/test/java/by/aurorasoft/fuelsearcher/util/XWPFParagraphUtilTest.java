package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class XWPFParagraphUtilTest {

    @Test
    public void elementShouldBeEmptyParagraph() {
        final IBodyElement givenElement = createParagraph(" \u00A0   \u00A0   ");

        final boolean actual = isEmptyParagraph(givenElement);
        assertTrue(actual);
    }

    @Test
    public void elementShouldNotBeEmptyParagraphBecauseOfItIsNotEmpty() {
        final IBodyElement givenElement = createParagraph(" \u00A0 text  \u00A0   ");

        final boolean actual = isEmptyParagraph(givenElement);
        assertFalse(actual);
    }

    @Test
    public void elementShouldNotBeEmptyParagraphBecauseOfItIsNotParagraph() {
        final IBodyElement givenElement = mock(IBodyElement.class);

        final boolean actual = isEmptyParagraph(givenElement);
        assertFalse(actual);
    }

    @Test
    public void textLinesShouldBeExtracted() {
        final XWPFParagraph givenParagraph = createParagraph(
                " \u00A0 text1\ntext2\n\n\n\n\n\n  \u00A0 text3  \u00A0  \n\n text4  \u00A0   "
        );

        final Stream<String> actual = extractTextLines(givenParagraph);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of("text1", "text2", "text3", "text4");
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void emptyTextLinesShouldBeExtracted() {
        final XWPFParagraph givenParagraph = createParagraph(
                " \u00A0 \n\n\n\n\n\n\n  \u00A0   \u00A0  \n\n   \u00A0   "
        );

        final Stream<String> actual = extractTextLines(givenParagraph);
        final long actualCount = actual.count();
        assertEquals(0, actualCount);
    }

    @Test
    public void paragraphTextShouldBeExtracted() {
        final IBodyElement givenElement = createParagraph(" \u00A0 text   text  \u00A0   ");

        final String actual = extractParagraphText(givenElement);
        final String expected = "text   text";
        assertEquals(expected, actual);
    }

    private static XWPFParagraph createParagraph(final String text) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        when(paragraph.getText()).thenReturn(text);
        return paragraph;
    }
}
