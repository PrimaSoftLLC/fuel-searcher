package by.aurorasoft.fuelsearcher.util;

import by.aurorasoft.fuelsearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.*;
import java.util.stream.IntStream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isChildUnitedRow;
import static java.util.stream.IntStream.range;

@UtilityClass
public final class XWPFTableRowFilteringUtil {

    public static OptionalInt findIndexFirstRowByContent(final List<XWPFTableRow> rows,
                                                         final int contentCellIndex,
                                                         final String content) {
        return findIndexFirstMatchingContentRow(
                rows,
                0,
                contentCellIndex,
                content,
                XWPFTableRowUtil::isCellTextEqualIgnoringWhitespacesAndCase
        );
    }

    public static OptionalInt findIndexFirstRowByContentRegex(final List<XWPFTableRow> rows,
                                                              final int startFindingIndex,
                                                              final int contentCellIndex,
                                                              final String regex) {
        return findIndexFirstMatchingContentRow(
                rows,
                startFindingIndex,
                contentCellIndex,
                regex,
                XWPFTableRowUtil::isCellTextMatchRegex
        );
    }

    //returns several united rows
    public static List<XWPFTableRow> findUnitedRowsByContent(final List<XWPFTableRow> rows,
                                                             final int contentCellIndex,
                                                             final String content) {
        return range(0, rows.size())
                .filter(i -> isCellTextEqualIgnoringWhitespacesAndCase(rows.get(i), contentCellIndex, content))
                .mapToObj(indexFirstRow -> extractUnitedRows(rows, indexFirstRow, contentCellIndex))
                .flatMap(Collection::stream)
                .toList();
    }

    public static IntStream findRowIndexesByContent(final List<XWPFTableRow> rows,
                                                    final int contentCellIndex,
                                                    final String content) {
        return range(0, rows.size())
                .filter(i -> rows.get(i).getCell(contentCellIndex).getText().equals(content));
    }


    public static Optional<XWPFTableRow> findFirstRowByContent(final List<XWPFTableRow> rows,
                                                               final int contentCellIndex,
                                                               final String content) {
        return findIndexFirstRowByContent(rows, contentCellIndex, content)
                .stream()
                .mapToObj(rows::get)
                .findFirst();
    }

    public static OptionalInt findIndexFirstCellByContent(final XWPFTableRow row, final String content) {
        final List<XWPFTableCell> cells = row.getTableCells();
        return range(0, cells.size())
                .filter(i -> Objects.equals(content, cells.get(i).getText()))
                .findFirst();
    }

    public static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static OptionalInt findIndexFirstMatchingContentRow(final List<XWPFTableRow> rows,
                                                                final int startFindingIndex,
                                                                final int contentCellIndex,
                                                                final String content,
                                                                final RowContentMatcher matcher) {
        return range(startFindingIndex, rows.size())
                .filter(i -> matcher.isMatch(rows.get(i), contentCellIndex, content))
                .findFirst();
    }

    private static List<XWPFTableRow> extractUnitedRows(final List<XWPFTableRow> rows,
                                                        final int indexFirstRow,
                                                        final int contentCellIndex) {
        final int nextIndexLastChildUnitedRow = findNextIndexLastChildUnitedRow(
                rows,
                indexFirstRow,
                contentCellIndex
        );
        return rows.subList(indexFirstRow, nextIndexLastChildUnitedRow);
    }

    private static int findNextIndexLastChildUnitedRow(final List<XWPFTableRow> rows,
                                                       final int indexFirstRow,
                                                       final int contentCellIndex) {
        return range(indexFirstRow + 1, rows.size())
                .dropWhile(rowIndex -> isChildUnitedRow(rows.get(rowIndex), contentCellIndex))
                .findFirst()
                .orElse(rows.size());
    }

    @FunctionalInterface
    private interface RowContentMatcher {
        boolean isMatch(final XWPFTableRow row, final int contentCellIndex, final String expected);
    }
}
