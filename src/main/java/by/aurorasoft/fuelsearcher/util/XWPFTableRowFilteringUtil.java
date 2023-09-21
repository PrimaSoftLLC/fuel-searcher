package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.*;
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

    //TODO: refactor and test
    public static Stream<XWPFTableRow> findRowsWithNotNullAndNotEmptyCell(final List<XWPFTableRow> rows,
                                                                          final int cellIndex) {
        return rows.stream()
                .filter(
                        row -> !isCellNullOrEmpty(
                                row,
                                cellIndex
                        )
                );
    }

    //TODO: refactor and test
    public static Stream<XWPFTableRow> findRowsWithCellMatchingRegex(final List<XWPFTableRow> rows,
                                                                     final int cellIndex,
                                                                     final String regex) {
        return rows.stream()
                .filter(
                        row -> isCellTextMatchRegex(
                                row,
                                cellIndex,
                                regex
                        )
                );
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
        return findMatchingContentRowIndexes(
                rows,
                startFindingIndex,
                contentCellIndex,
                content,
                matcher
        ).findFirst();
    }

    private static IntStream findMatchingContentRowIndexes(final List<XWPFTableRow> rows,
                                                           final int startFindingIndex,
                                                           final int contentCellIndex,
                                                           final String content,
                                                           final RowContentMatcher matcher) {
        return range(startFindingIndex, rows.size())
                .filter(
                        i -> matcher.isMatch(
                                rows.get(i),
                                contentCellIndex,
                                content
                        )
                );
    }

    private static IntStream findRowIndexesByContent(final List<XWPFTableRow> rows,
                                                     final int contentCellIndex,
                                                     final String content) {
        return findMatchingContentRowIndexes(
                rows,
                0,
                contentCellIndex,
                content,
                XWPFTableRowUtil::isCellTextEqualIgnoringWhitespacesAndCase
        );
    }

    private static List<XWPFTableRow> findUnitedRows(final List<XWPFTableRow> rows,
                                                     final int firstRowIndex,
                                                     final int contentCellIndex) {
        final int lastChildNextIndexUnitedRow = findLastChildNextIndexUnitedRow(
                rows,
                firstRowIndex,
                contentCellIndex
        );
        return rows.subList(firstRowIndex, lastChildNextIndexUnitedRow);
    }

    private static int findLastChildNextIndexUnitedRow(final List<XWPFTableRow> rows,
                                                       final int firstRowIndex,
                                                       final int contentCellIndex) {
        final int lastRowNextIndex = rows.size();
        return range(firstRowIndex + 1, rows.size())
                .dropWhile(rowIndex -> isChildUnitedRow(rows.get(rowIndex), contentCellIndex))
                .findFirst()
                .orElse(lastRowNextIndex);
    }

    private static List<XWPFTableRow> findGroupRows(final List<XWPFTableRow> rows,
                                                    final int firstGroupRowIndex,
                                                    final int filtrationCellIndex,
                                                    final String groupValueRegex) {
        final int nextIndexLastGroupRow = findNextIndexLastGroupRow(
                rows,
                firstGroupRowIndex,
                filtrationCellIndex,
                groupValueRegex
        );
        return rows.subList(firstGroupRowIndex, nextIndexLastGroupRow);
    }

    private static int findNextIndexLastGroupRow(final List<XWPFTableRow> rows,
                                                 final int firstGroupRowIndex,
                                                 final int filtrationCellIndex,
                                                 final String groupValueRegex) {
        final int lastRowNextIndex = rows.size();
        return findIndexFirstRowByContentRegex(
                rows,
                firstGroupRowIndex,
                filtrationCellIndex,
                groupValueRegex
        ).orElse(lastRowNextIndex);
    }

    @FunctionalInterface
    private interface RowContentMatcher {
        boolean isMatch(final XWPFTableRow row, final int contentCellIndex, final String expected);
    }
}
