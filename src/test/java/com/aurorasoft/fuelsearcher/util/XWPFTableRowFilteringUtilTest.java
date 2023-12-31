package com.aurorasoft.fuelsearcher.util;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.*;
import static java.util.stream.IntStream.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class XWPFTableRowFilteringUtilTest extends AbstractContextTest {
    private static final String FIRST_TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    private static final int INDEX_FIRST_ROW_FILTERED_BY_GROUP = 5;
    private static final int INDEX_LAST_ROW_FILTERED_BY_GROUP = 56;

    @Autowired
    private FuelDocument fuelDocument;

    private List<XWPFTableRow> rows;

    @Before
    public void initializeRows() {
        this.rows = findSubTableRowsOfFirstTable(this.fuelDocument);
    }

    @Test
    public void unitedRowsShouldBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenContentCellIndex = 2;
        final String givenContent = "ППУ-13";

        final List<XWPFTableRow> actual = findUnitedRowsByContent(
                givenRows,
                givenContentCellIndex,
                givenContent
        );

        final List<Integer> expectedRowIndexes = List.of(8, 9, 10, 11, 16, 17, 18, 19);
        assertEquals(expectedRowIndexes.size(), actual.size());

        final boolean rowFilteringSuccess = isRowFilteringSuccess(givenRows, actual, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void lastUnitedRowsInGroupShouldBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenContentCellIndex = 2;
        final String givenContent = "ППО-9-30/45";

        final List<XWPFTableRow> actual = findUnitedRowsByContent(
                givenRows,
                givenContentCellIndex,
                givenContent
        );

        final List<Integer> expectedRowIndexes = List.of(48, 49, 50, 51);
        assertEquals(expectedRowIndexes.size(), actual.size());

        final boolean rowFilteringSuccess = isRowFilteringSuccess(givenRows, actual, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void unitedRowsShouldNotBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenContentCellIndex = 2;
        final String givenContent = "not-existing-mechanism";

        final List<XWPFTableRow> actual = findUnitedRowsByContent(
                givenRows,
                givenContentCellIndex,
                givenContent
        );
        assertTrue(actual.isEmpty());
    }

    @Test
    public void firstRowShouldBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenContentCellIndex = 1;
        final String givenContent = "Беларус 3525";

        final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
                givenRows,
                givenContentCellIndex,
                givenContent
        );
        assertTrue(optionalActual.isPresent());
        final XWPFTableRow actual = optionalActual.get();

        final int expectedRowIndex = 20;
        final boolean rowFilteringSuccess = isRowFilteringSuccess(givenRows, actual, expectedRowIndex);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void firstRowShouldNotBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenContentCellIndex = 1;
        final String givenContent = "not-existing-tractor";

        final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
                givenRows,
                givenContentCellIndex,
                givenContent
        );
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void firstCellIndexShouldBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenRowIndex = 4;
        final XWPFTableRow givenRow = givenRows.get(givenRowIndex);
        final String givenContent = "9.4";

        final OptionalInt optionalActual = findFirstCellIndexByContent(givenRow, givenContent);
        assertTrue(optionalActual.isPresent());
        final int actual = optionalActual.getAsInt();
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void firstCellIndexShouldNotBeFoundByContent() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final int givenRowIndex = 4;
        final XWPFTableRow givenRow = givenRows.get(givenRowIndex);
        final String givenContent = "not-existing-content";

        final OptionalInt optionalActual = findFirstCellIndexByContent(givenRow, givenContent);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void rowsShouldBeFoundByGroup() {
        final String givenGroupValue = "Удельное сопротивление 54...59 кПа";
        final String givenGroupValueRegex = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
        final int givenGroupValueCellIndex = 0;

        final List<XWPFTableRow> actual = findRowsByGroup(
                this.rows,
                givenGroupValue,
                givenGroupValueRegex,
                givenGroupValueCellIndex
        );

        final int expectedFirstGroupRowIndex = 58;
        final int expectedLastGroupRowIndex = 109;
        final List<Integer> expectedRowIndexes = rangeClosed(expectedFirstGroupRowIndex, expectedLastGroupRowIndex)
                .boxed()
                .toList();
        assertEquals(expectedRowIndexes.size(), actual.size());

        final boolean rowFilteringSuccess = isRowFilteringSuccess(this.rows, actual, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void lastGroupRowsShouldBeFound() {
        final String givenGroupValue = "Удельное сопротивление 60...65 кПа";
        final String givenGroupValueRegex = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
        final int givenGroupValueCellIndex = 0;

        final List<XWPFTableRow> actual = findRowsByGroup(
                this.rows,
                givenGroupValue,
                givenGroupValueRegex,
                givenGroupValueCellIndex
        );

        final int expectedFirstGroupRowIndex = 111;
        final int expectedLastGroupRowIndex = 162;
        final List<Integer> expectedRowIndexes = rangeClosed(expectedFirstGroupRowIndex, expectedLastGroupRowIndex)
                .boxed()
                .toList();
        assertEquals(expectedRowIndexes.size(), actual.size());

        final boolean rowFilteringSuccess = isRowFilteringSuccess(this.rows, actual, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void rowsShouldNotBeFoundByGroup() {
        final String givenGroupValue = "Удельное сопротивление -5...-1 кПа";
        final String givenGroupValueRegex = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
        final int givenGroupValueCellIndex = 0;

        final List<XWPFTableRow> actual = findRowsByGroup(
                this.rows,
                givenGroupValue,
                givenGroupValueRegex,
                givenGroupValueCellIndex
        );
        assertTrue(actual.isEmpty());
    }

    @Test
    public void rowsWithNotNullAndNotEmptyCellShouldBeFound() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final Stream<XWPFTableRow> actual = findRowsWithNotNullAndNotEmptyCell(
                givenRows,
                1
        );
        final List<XWPFTableRow> actualAsList = actual.toList();

        final int indexGroupFirstRow = 0;
        final int indexGroupLastRow = 51;
        final int unitedRowsCount = 4;
        final List<Integer> expectedRowIndexes = iterate(
                indexGroupFirstRow,
                i -> i <= indexGroupLastRow,
                i -> i + unitedRowsCount
        ).boxed().toList();

        final boolean rowFilteringSuccess = isRowFilteringSuccess(givenRows, actualAsList, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void rowsWithNotNullAndNotEmptyCellShouldNotBeFound() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows().subList(1, 4);

        final Stream<XWPFTableRow> actual = findRowsWithNotNullAndNotEmptyCell(
                givenRows,
                1
        );

        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
    }

    @Test
    public void rowsWithCellMatchingRegexShouldBeFound() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final Stream<XWPFTableRow> actual = findRowsWithCellMatchingRegex(
                givenRows,
                2,
                "8"
        );
        final List<XWPFTableRow> actualAsList = actual.toList();

        final List<Integer> expectedRowIndexes = List.of(4, 24);

        final boolean rowFilteringSuccess = isRowFilteringSuccess(givenRows, actualAsList, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void rowsWithCellMatchingRegexShouldNotBeFound() {
        final List<XWPFTableRow> givenRows = this.extractFirstGroupRows();
        final Stream<XWPFTableRow> actual = findRowsWithCellMatchingRegex(
                givenRows,
                2,
                "0"
        );

        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
    }

    private static List<XWPFTableRow> findSubTableRowsOfFirstTable(final FuelDocument fuelDocument) {
        return fuelDocument.tables()
                .stream()
                .filter(XWPFTableRowFilteringUtilTest::isFirstTable)
                .findFirst()
                .map(FuelTable::elements)
                .map(elements -> elements.get(0))
                .map(element -> (XWPFTable) element)
                .map(XWPFTable::getRows)
                .orElseThrow(() -> new IllegalArgumentException("Table for testing wasn't found"));
    }

    private static boolean isFirstTable(final FuelTable table) {
        final String tableName = table.name();
        return Objects.equals(tableName, FIRST_TABLE_NAME);
    }

    private List<XWPFTableRow> extractFirstGroupRows() {
        return this.rows.subList(
                INDEX_FIRST_ROW_FILTERED_BY_GROUP,
                INDEX_LAST_ROW_FILTERED_BY_GROUP + 1
        );
    }

    private static boolean isRowFilteringSuccess(final List<XWPFTableRow> initialRows,
                                                 final List<XWPFTableRow> actualFilteredRows,
                                                 final List<Integer> expectedFilteredRowIndexes) {
        return range(0, actualFilteredRows.size())
                .allMatch(
                        i -> isRowFilteringSuccess(
                                initialRows,
                                actualFilteredRows.get(i),
                                expectedFilteredRowIndexes.get(i)
                        )
                );
    }

    private static boolean isRowFilteringSuccess(final List<XWPFTableRow> initialRows,
                                                 final XWPFTableRow actualFilteredRow,
                                                 final int expectedRowIndex) {
        final XWPFTableRow expectedRow = initialRows.get(expectedRowIndex);
        return expectedRow == actualFilteredRow;
    }
}
