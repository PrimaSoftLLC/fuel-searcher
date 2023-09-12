package by.aurorasoft.fuelsearcher.util;

import by.aurorasoft.fuelsearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.*;
import java.util.stream.IntStream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.extractText;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static java.util.stream.IntStream.range;

@UtilityClass
public final class XWPFTableRowFilteringUtil {
    private static final String EMPTY_STRING = "";

    public static OptionalInt findIndexFirstRowByContent(final List<XWPFTableRow> rows,
                                                         final int cellIndexWithContent,
                                                         final String content) {
        return findIndexFirstMatchingContentRow(
                rows,
                0,
                cellIndexWithContent,
                content,
                XWPFTableRowUtil::isCellTextEqualIgnoringWhitespacesAndCase
        );
    }

    public static OptionalInt findIndexFirstRowByContentRegex(final List<XWPFTableRow> rows,
                                                              final int startFindingIndex,
                                                              final int cellIndexWithContent,
                                                              final String regex) {
        return findIndexFirstMatchingContentRow(
                rows,
                startFindingIndex,
                cellIndexWithContent,
                regex,
                XWPFTableRowUtil::isCellTextMatchRegex
        );
    }

    //returns several united rows
    public static List<XWPFTableRow> findUnitedRowsByContent(final List<XWPFTableRow> rows,
                                                             final int cellIndexWithContent,
                                                             final String content) {
        return range(0, rows.size())
                .filter(i -> isCellTextEqualIgnoringWhitespacesAndCase(rows.get(i), cellIndexWithContent, content))
                .mapToObj(indexFirstRow -> extractUnitedRows(rows, indexFirstRow, cellIndexWithContent))
                .flatMap(Collection::stream)
                .toList();
    }

    public static IntStream findRowIndexesByContent(final List<XWPFTableRow> rows,
                                                    final int cellIndexWithContent,
                                                    final String content) {
        return range(0, rows.size())
                .filter(i -> rows.get(i).getCell(cellIndexWithContent).getText().equals(content));
    }


    public static Optional<XWPFTableRow> findFirstRowByContent(final List<XWPFTableRow> rows,
                                                               final int cellIndexWithContent,
                                                               final String content) {
        return findIndexFirstRowByContent(rows, cellIndexWithContent, content)
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
                                                                final int cellIndexWithContent,
                                                                final String content,
                                                                final ThreeArgumentPredicate<XWPFTableRow, Integer, String> predicateToMatch) {
        return range(startFindingIndex, rows.size())
                .filter(i -> predicateToMatch.test(rows.get(i), cellIndexWithContent, content))
                .findFirst();
    }

    private static List<XWPFTableRow> extractUnitedRows(final List<XWPFTableRow> rows,
                                                        final int indexFirstRow,
                                                        final int cellIndexWithContent) {
        final int nextIndexLastRow = findNextIndexLastRowOfUnitedRows(rows, indexFirstRow, cellIndexWithContent);
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static int findNextIndexLastRowOfUnitedRows(final List<XWPFTableRow> rows,
                                                        final int indexFirstRow,
                                                        final int cellIndexWithContent) {
        return range(indexFirstRow + 1, rows.size())
                .dropWhile(rowIndex -> isRowOfUnitedRows(rows.get(rowIndex), cellIndexWithContent))
                .findFirst()
                .orElse(rows.size());
    }

    private static boolean isRowOfUnitedRows(final XWPFTableRow row, final int cellIndexWithContent) {
        final XWPFTableCell cellWithContent = row.getCell(cellIndexWithContent);
        return cellWithContent == null || Objects.equals(extractText(cellWithContent), EMPTY_STRING);
    }

    @FunctionalInterface
    private interface ThreeArgumentPredicate<F, S, T> {
        boolean test(final F first, final S second, final T third);
    }
}
