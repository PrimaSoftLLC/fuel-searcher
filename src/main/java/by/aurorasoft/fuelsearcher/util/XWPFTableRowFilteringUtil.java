package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isChildUnitedRow;
import static java.util.stream.IntStream.range;

@UtilityClass
public final class XWPFTableRowFilteringUtil {

    //returns several united rows
    public static List<XWPFTableRow> findUnitedRowsByContent(final List<XWPFTableRow> rows,
                                                             final int contentCellIndex,
                                                             final String content) {
        return findRowIndexesByContent(rows, contentCellIndex, content)
                .mapToObj(firstRowIndex -> findUnitedRows(rows, firstRowIndex, contentCellIndex))
                .flatMap(Collection::stream)
                .toList();
    }

    public static Optional<XWPFTableRow> findFirstRowByContent(final List<XWPFTableRow> rows,
                                                               final int contentCellIndex,
                                                               final String content) {
        return findIndexFirstRowByContent(rows, contentCellIndex, content)
                .stream()
                .mapToObj(rows::get)
                .findFirst();
    }

    public static OptionalInt findFirstCellIndexByContent(final XWPFTableRow row, final String content) {
        final List<XWPFTableCell> cells = row.getTableCells();
        return range(0, cells.size())
                .filter(i -> isCellTextEqualIgnoringWhitespacesAndCase(cells.get(i), content))
                .findFirst();
    }

    public static List<XWPFTableRow> findRowsByGroup(final List<XWPFTableRow> rows,
                                                     final String groupValue,
                                                     final String groupValueRegex,
                                                     final int groupValueCellIndex) {
        return findRowIndexesByContent(rows, groupValueCellIndex, groupValue)
                .map(groupValueRowIndex -> groupValueRowIndex + 1)
                .mapToObj(
                        firstGroupRowIndex -> findGroupRows(
                                rows,
                                firstGroupRowIndex,
                                groupValueCellIndex,
                                groupValueRegex
                        )
                )
                .flatMap(Collection::stream)
                .toList();
    }

    private static OptionalInt findIndexFirstRowByContent(final List<XWPFTableRow> rows,
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

    private static OptionalInt findIndexFirstRowByContentRegex(final List<XWPFTableRow> rows,
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

    private static OptionalInt findIndexFirstMatchingContentRow(final List<XWPFTableRow> rows,
                                                                final int startFindingIndex,
                                                                final int contentCellIndex,
                                                                final String content,
                                                                final RowContentMatcher matcher) {
        return range(startFindingIndex, rows.size())
                .filter(i -> matcher.isMatch(rows.get(i), contentCellIndex, content))
                .findFirst();
    }

    private static IntStream findRowIndexesByContent(final List<XWPFTableRow> rows,
                                                     final int contentCellIndex,
                                                     final String content) {
        return range(0, rows.size())
                .filter(i -> isCellTextEqualIgnoringWhitespacesAndCase(rows.get(i), contentCellIndex, content));
    }

    private static List<XWPFTableRow> findUnitedRows(final List<XWPFTableRow> rows,
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
        final int nextIndexLastRow = rows.size();
        return range(indexFirstRow + 1, rows.size())
                .dropWhile(rowIndex -> isChildUnitedRow(rows.get(rowIndex), contentCellIndex))
                .findFirst()
                .orElse(nextIndexLastRow);
    }

    private static List<XWPFTableRow> findGroupRows(final List<XWPFTableRow> rows,
                                                    final int indexFirstGroupRow,
                                                    final int filtrationCellIndex,
                                                    final String groupValueRegex) {
        final int nextIndexLastGroupRow = findNextIndexLastGroupRow(
                rows,
                indexFirstGroupRow,
                filtrationCellIndex,
                groupValueRegex
        );
        return rows.subList(indexFirstGroupRow, nextIndexLastGroupRow);
    }

    private static int findNextIndexLastGroupRow(final List<XWPFTableRow> rows,
                                                 final int indexFirstGroupRow,
                                                 final int filtrationCellIndex,
                                                 final String groupValueRegex) {
        final int nextIndexLastRow = rows.size();
        return findIndexFirstRowByContentRegex(
                rows,
                indexFirstGroupRow,
                filtrationCellIndex,
                groupValueRegex
        ).orElse(nextIndexLastRow);
    }

    @FunctionalInterface
    private interface RowContentMatcher {
        boolean isMatch(final XWPFTableRow row, final int contentCellIndex, final String expected);
    }
}
