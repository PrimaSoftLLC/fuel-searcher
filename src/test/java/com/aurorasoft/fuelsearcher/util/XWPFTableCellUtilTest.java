package com.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.areEqualConsideringOnlyLettersAndDigits;
import static com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractText;
import static com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.isEmpty;
import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.*;
import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class XWPFTableCellUtilTest {

    @Test
    public void cellShouldBeEmpty() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            markParagraphAsEmpty(firstGivenParagraph, mockedParagraphUtil);
            markParagraphAsEmpty(secondGivenParagraph, mockedParagraphUtil);

            final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
            assertTrue(actual);
        }
    }

    @Test
    public void cellShouldNotBeEmpty() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            markParagraphAsEmpty(firstGivenParagraph, mockedParagraphUtil);
            markParagraphAsNotEmpty(secondGivenParagraph, mockedParagraphUtil);

            final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
            assertFalse(actual);
        }
    }

    @Test
    public void cellWithoutParagraphsShouldBeEmpty() {
        final XWPFTableCell givenCell = createCell();

        final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
        assertTrue(actual);
    }

    @Test
    public void cellTextShouldBeExtracted() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        final String givenTextFirstParagraph = "content-1";
        final String givenTextSecondParagraph = "content-2";

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            bindTextWithParagraph(firstGivenParagraph, givenTextFirstParagraph, mockedParagraphUtil);
            bindTextWithParagraph(secondGivenParagraph, givenTextSecondParagraph, mockedParagraphUtil);

            final String actual = XWPFTableCellUtil.extractText(givenCell);
            final String expected = "content-1 content-2";
            assertEquals(expected, actual);
        }
    }

    @Test
    public void textOfCellWithoutParagraphsShouldBeExtracted() {
        final XWPFTableCell givenCell = createCell();

        final String actual = XWPFTableCellUtil.extractText(givenCell);
        final String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void doubleShouldBeDefined() {
        final double givenDouble = 0.;

        final boolean actual = isNotDefinedDouble(givenDouble);
        assertFalse(actual);
    }

    @Test
    public void doubleShouldBeNotDefined() {
        final boolean actual = isNotDefinedDouble(NaN);
        assertTrue(actual);
    }

    @Test
    public void doubleShouldBeExtracted() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        final String givenTextFirstParagraph = "44.5";
        final String givenTextSecondParagraph = "";

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            bindTextWithParagraph(firstGivenParagraph, givenTextFirstParagraph, mockedParagraphUtil);
            bindTextWithParagraph(secondGivenParagraph, givenTextSecondParagraph, mockedParagraphUtil);

            final double actual = extractDouble(givenCell);
            final double expected = 44.5;
            assertEquals(expected, actual, 0.);
        }
    }

    @Test
    public void notDefinedDoubleShouldBeExtracted() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        final String givenTextFirstParagraph = "-";
        final String givenTextSecondParagraph = "";

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            bindTextWithParagraph(firstGivenParagraph, givenTextFirstParagraph, mockedParagraphUtil);
            bindTextWithParagraph(secondGivenParagraph, givenTextSecondParagraph, mockedParagraphUtil);

            final double actual = extractDouble(givenCell);
            assertTrue(isNaN(actual));
        }
    }

    @Test
    public void cellTextShouldBeEqualGivenContentConsideringOnlyLettersAndDigits() {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class);
             final MockedStatic<XWPFContentUtil> mockedContentComparingUtil = mockStatic(XWPFContentUtil.class)) {
            final String givenText = "cell-text";
            final XWPFTableCell givenCell = createCell(givenText, mockedParagraphUtil);

            final String givenExpected = "CELL     text";
            mockedContentComparingUtil.when(
                    () -> areEqualConsideringOnlyLettersAndDigits(eq(givenText), same(givenExpected))
            ).thenReturn(true);

            final boolean actual = isCellTextEqualConsideringOnlyLettersAndDigits(givenCell, givenExpected);
            assertTrue(actual);
        }
    }

    @Test
    public void cellTextShouldNotBeEqualGivenContentConsideringOnlyLettersAndDigits() {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class);
             final MockedStatic<XWPFContentUtil> mockedContentComparingUtil = mockStatic(XWPFContentUtil.class)) {
            final String givenText = "cell-text";
            final XWPFTableCell givenCell = createCell(givenText, mockedParagraphUtil);

            final String givenExpected = "CELL -    text text";
            mockedContentComparingUtil.when(
                    () -> areEqualConsideringOnlyLettersAndDigits(eq(givenText), same(givenExpected))
            ).thenReturn(false);

            final boolean actual = isCellTextEqualConsideringOnlyLettersAndDigits(givenCell, givenExpected);
            assertFalse(actual);
        }
    }

    @Test
    public void cellTextShouldMatchRegex() {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            final String givenText = "cell-text";
            final XWPFTableCell givenCell = createCell(givenText, mockedParagraphUtil);

            final String givenRegex = "cell-text";

            final boolean actual = isCellTextMatchRegex(givenCell, givenRegex);
            assertTrue(actual);
        }
    }

    @Test
    public void cellTextShouldNotMatchRegex() {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            final String givenText = "cell-text";
            final XWPFTableCell givenCell = createCell(givenText, mockedParagraphUtil);

            final String givenRegex = "cell-tex";

            final boolean actual = isCellTextMatchRegex(givenCell, givenRegex);
            assertFalse(actual);
        }
    }

    private static XWPFTableCell createCell(final XWPFParagraph... paragraphs) {
        final List<XWPFParagraph> paragraphsAsList = paragraphs != null ? asList(paragraphs) : emptyList();
        final XWPFTableCell cell = mock(XWPFTableCell.class);
        when(cell.getParagraphs()).thenReturn(paragraphsAsList);
        return cell;
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableCell createCell(final String text,
                                            final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        bindTextWithParagraph(paragraph, text, mockedParagraphUtil);
        return createCell(paragraph);
    }

    private static void markParagraphAsEmpty(final XWPFParagraph paragraph,
                                             final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        markParagraphByEmpty(paragraph, mockedParagraphUtil, true);
    }

    private static void markParagraphAsNotEmpty(final XWPFParagraph paragraph,
                                                final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        markParagraphByEmpty(paragraph, mockedParagraphUtil, false);
    }

    private static void markParagraphByEmpty(final XWPFParagraph paragraph,
                                             final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil,
                                             final boolean empty) {
        mockedParagraphUtil.when(() -> isEmpty(same(paragraph))).thenReturn(empty);
    }

    private static void bindTextWithParagraph(final XWPFParagraph paragraph,
                                              final String text,
                                              final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        mockedParagraphUtil.when(() -> extractText(same(paragraph))).thenReturn(text);
    }
}
