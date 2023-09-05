package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
    public void paragraphShouldBeEmpty() {
        final XWPFParagraph givenParagraph = createParagraph(" \u00A0   \u00A0   ");

        final boolean actual = isEmpty(givenParagraph);
        assertTrue(actual);
    }

    @Test
    public void paragraphShouldNotBeEmpty() {
        final XWPFParagraph givenParagraph = createParagraph(" \u00A0 text   \u00A0   ");

        final boolean actual = isEmpty(givenParagraph);
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
    public void elementTextShouldBeExtracted() {
        final IBodyElement givenElement = createParagraph(" \u00A0 text   text  \u00A0   ");

        final String actual = extractParagraphText(givenElement);
        final String expected = "text   text";
        assertEquals(expected, actual);
    }

    @Test
    public void elementEmptyTextShouldBeExtracted() {
        final IBodyElement givenElement = createParagraph(" \u00A0      \u00A0   ");

        final String actual = extractParagraphText(givenElement);
        final String expected = "";
        assertEquals(expected, actual);
    }

    @Test(expected = ClassCastException.class)
    public void elementTextShouldNotBeExtractedBecauseOfItIsNotParagraph() {
        final IBodyElement givenElement = mock(IBodyElement.class);

        extractParagraphText(givenElement);
    }

    @Test
    public void paragraphTextShouldBeExtracted() {
        final XWPFParagraph givenParagraph = createParagraph(" \u00A0 text   text  \u00A0   ");

        final String actual = extractText(givenParagraph);
        final String expected = "text   text";
        assertEquals(expected, actual);
    }

    @Test
    public void paragraphEmptyTextShouldBeExtracted() {
        final XWPFParagraph givenParagraph = createParagraph(" \u00A0      \u00A0   ");

        final String actual = extractText(givenParagraph);
        final String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void paragraphShouldBeCreatedByGroupContent() {
        final Matcher givenMatcher = mock(Matcher.class);
        final int givenGroupNumber = 1;
        final String givenContent = "group-content";
        when(givenMatcher.group(eq(givenGroupNumber))).thenReturn(givenContent);

        final XWPFDocument givenDocument = mock(XWPFDocument.class);
        final XWPFParagraph givenParagraph = mock(XWPFParagraph.class);
        when(givenDocument.createParagraph()).thenReturn(givenParagraph);

        final XWPFRun givenRun = mock(XWPFRun.class);
        when(givenParagraph.createRun()).thenReturn(givenRun);

        final XWPFParagraph actual = createParagraphByGroupContent(givenMatcher, givenGroupNumber, givenDocument);
        assertSame(givenParagraph, actual);

        verify(givenRun, times(1)).setText(same(givenContent));
    }

    private static XWPFParagraph createParagraph(final String text) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        when(paragraph.getText()).thenReturn(text);
        return paragraph;
    }
}
