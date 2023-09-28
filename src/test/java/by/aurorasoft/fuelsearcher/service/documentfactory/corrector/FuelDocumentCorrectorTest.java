package by.aurorasoft.fuelsearcher.service.documentfactory.corrector;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.corrector.FuelDocumentCorrector;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.corrector.paragraphcorrector.ParagraphCorrector;
import by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isEmpty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class FuelDocumentCorrectorTest {

    @Mock
    private ParagraphCorrector firstMockedParagraphCorrector;

    @Mock
    private ParagraphCorrector secondMockedParagraphCorrector;

    private FuelDocumentCorrector documentCorrector;

    @Captor
    private ArgumentCaptor<XWPFParagraph> paragraphArgumentCaptor;


    @Before
    public void initializeDocumentCorrector() {
        this.documentCorrector = new FuelDocumentCorrector(
                List.of(
                        this.firstMockedParagraphCorrector,
                        this.secondMockedParagraphCorrector
                )
        );
    }

    @Test
    public void documentShouldBeCorrected() {
        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
            final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
            final XWPFParagraph thirdGivenParagraph = mock(XWPFParagraph.class);
            final XWPFParagraph fourthGivenParagraph = mock(XWPFParagraph.class);
            final XWPFParagraph fifthGivenParagraph = mock(XWPFParagraph.class);
            final XWPFParagraph sixthGivenParagraph = mock(XWPFParagraph.class);

            final XWPFTableCell firstGivenCell = createNotEmptyCell(
                    List.of(firstGivenParagraph, secondGivenParagraph),
                    mockedCellUtil
            );
            final XWPFTable firstGivenTable = createTable(firstGivenCell);

            final XWPFTableCell secondGivenCell = createEmptyCell(
                    List.of(thirdGivenParagraph, fourthGivenParagraph),
                    mockedCellUtil
            );
            final XWPFTable secondGivenTable = createTable(secondGivenCell);

            final FuelTable firstGivenFuelTable = mock(FuelTable.class);
            when(firstGivenFuelTable.elements()).thenReturn(List.of(fifthGivenParagraph, firstGivenTable));

            final FuelTable secondGivenFuelTable = mock(FuelTable.class);
            when(secondGivenFuelTable.elements()).thenReturn(List.of(sixthGivenParagraph, secondGivenTable));

            final FuelDocument givenDocument = mock(FuelDocument.class);
            when(givenDocument.tables()).thenReturn(List.of(firstGivenFuelTable, secondGivenFuelTable));

            this.documentCorrector.correct(givenDocument);

            final int amountOfParagraphsNotInEmptyCells = 4;
            verify(this.firstMockedParagraphCorrector, times(amountOfParagraphsNotInEmptyCells))
                    .correct(this.paragraphArgumentCaptor.capture());
            verify(this.secondMockedParagraphCorrector, times(amountOfParagraphsNotInEmptyCells))
                    .correct(this.paragraphArgumentCaptor.capture());

            final List<XWPFParagraph> expectedCapturedParagraph = List.of(
                    fifthGivenParagraph, firstGivenParagraph,
                    secondGivenParagraph, sixthGivenParagraph,
                    fifthGivenParagraph, firstGivenParagraph,
                    secondGivenParagraph, sixthGivenParagraph
            );
            final List<XWPFParagraph> actualCapturedParagraph = this.paragraphArgumentCaptor.getAllValues();
            assertEquals(expectedCapturedParagraph, actualCapturedParagraph);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void documentShouldNotBeCorrectedBecauseOfOneOfElementsIsNotParagraphOrTable() {
        final IBodyElement givenElement = mock(IBodyElement.class);
        final FuelTable givenFuelTable = mock(FuelTable.class);
        when(givenFuelTable.elements()).thenReturn(List.of(givenElement));

        final FuelDocument givenDocument = mock(FuelDocument.class);
        when(givenDocument.tables()).thenReturn(List.of(givenFuelTable));

        this.documentCorrector.correct(givenDocument);
    }

    private static XWPFTable createTable(final XWPFTableCell cell) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        when(row.getTableCells()).thenReturn(List.of(cell));
        final XWPFTable table = mock(XWPFTable.class);
        when(table.getRows()).thenReturn(List.of(row));
        return table;
    }

    private static XWPFTableCell createEmptyCell(final List<XWPFParagraph> paragraphs,
                                                 final MockedStatic<XWPFTableCellUtil> mockedCellUtil) {
        return createCell(paragraphs, mockedCellUtil, true);
    }

    private static XWPFTableCell createNotEmptyCell(final List<XWPFParagraph> paragraphs,
                                                    final MockedStatic<XWPFTableCellUtil> mockedCellUtil) {
        return createCell(paragraphs, mockedCellUtil, false);
    }

    private static XWPFTableCell createCell(final List<XWPFParagraph> paragraphs,
                                            final MockedStatic<XWPFTableCellUtil> mockedCellUtil,
                                            final boolean emptyCell) {
        final XWPFTableCell cell = mock(XWPFTableCell.class);
        when(cell.getParagraphs()).thenReturn(paragraphs);
        mockedCellUtil.when(() -> isEmpty(same(cell))).thenReturn(emptyCell);
        return cell;
    }
}
