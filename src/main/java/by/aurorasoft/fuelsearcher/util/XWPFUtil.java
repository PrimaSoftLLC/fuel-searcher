package by.aurorasoft.fuelsearcher.util;

import by.aurorasoft.fuelsearcher.functionalinterface.ThreeArgumentPredicate;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Double.*;
import static java.util.Optional.empty;
import static java.util.stream.IntStream.range;

//TODO: refactor
@UtilityClass
public final class XWPFUtil {
    public static final double NOT_DEFINED_DOUBLE = NaN;

    //TODO: replace after loading in digits
    private static final String COMMA = ",";
    private static final String POINT = ".";
    private static final String EMPTY_STRING = "";

    public static boolean isNotDefinedDouble(final double value) {
        return isNaN(value);
    }

    public static boolean isCellContentMatch(final XWPFTableRow row, final int cellNumber, final String expected) {
        return isCellContentMatch(row, cellNumber, expected, String::equalsIgnoreCase);
    }

    public static boolean isCellContentMatchRegex(final XWPFTableRow row,
                                                  final int cellNumber,
                                                  final String expectedRegex) {
        return isCellContentMatch(row, cellNumber, expectedRegex, String::matches);
    }

    public static OptionalInt findIndexFirstRowByContent(final List<XWPFTableRow> rows,
                                                         final int cellIndexWithContent,
                                                         final String content) {
        return findIndexFirstMatchingContentRow(
                rows,
                0,
                cellIndexWithContent,
                content,
                XWPFUtil::isCellContentMatch
        );
    }

    public static OptionalInt findIndexFirstRowByContent(final List<XWPFTableRow> rows,
                                                         final int cellIndexWithContent,
                                                         final Specification specification,
                                                         final Function<Specification, String> contentExtractor) {
        final String content = contentExtractor.apply(specification);
        return findIndexFirstRowByContent(
                rows,
                cellIndexWithContent,
                content
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
                XWPFUtil::isCellContentMatchRegex
        );
    }

    //returns several united rows
    public static List<XWPFTableRow> findUnitedRowsByContent(final List<XWPFTableRow> rows,
                                                             final int cellIndexWithContent,
                                                             final String content) {
        return range(0, rows.size())
                .filter(i -> isCellContentMatch(rows.get(i), cellIndexWithContent, content))
                .mapToObj(indexFirstRow -> extractUnitedRows(rows, indexFirstRow, cellIndexWithContent))
                .flatMap(Collection::stream)
                .toList();
    }

    public static List<XWPFTableRow> findUnitedRowsByContent(final List<XWPFTableRow> rows,
                                                             final int cellIndexWithContent,
                                                             final Specification specification,
                                                             final Function<Specification, String> contentExtractor) {
        final String content = contentExtractor.apply(specification);
        return findUnitedRowsByContent(rows, cellIndexWithContent, content);
    }

    public static Optional<List<XWPFTableRow>> findRowsByContent(final List<XWPFTableRow> rows,
                                                                 final int cellIndexWithContent,
                                                                 final String content) {
        List<XWPFTableRow> tableRows = range(0, rows.size())
                .filter(i -> isCellContentMatch(rows.get(i), cellIndexWithContent, content))
                .mapToObj(i -> rows.get(i))
                .toList();
        return !tableRows.isEmpty() ? Optional.of(tableRows) : empty();
    }

    public static IntStream findRowIndexesByContent(final List<XWPFTableRow> rows,
                                                    final int cellIndexWithContent,
                                                    final String content) {
        return range(0, rows.size())
                .filter(i -> rows.get(i).getCell(cellIndexWithContent).getText().equals(content));
    }

    public static Optional<List<XWPFTableRow>> findRowsByContent(final List<XWPFTableRow> rows,
                                                                 final int cellIndexWithContent,
                                                                 final Specification specification,
                                                                 final Function<Specification, String> contentExtractor) {
        final String content = contentExtractor.apply(specification);
        return findRowsByContent(
                rows,
                cellIndexWithContent,
                content
        );
    }

    public static Optional<XWPFTableRow> findFirstRowByContent(final List<XWPFTableRow> rows,
                                                               final int cellIndexWithContent,
                                                               final String content) {
        return findIndexFirstRowByContent(rows, cellIndexWithContent, content)
                .stream()
                .mapToObj(rows::get)
                .findFirst();
    }

    public static Optional<XWPFTableRow> findFirstRowByContent(final List<XWPFTableRow> rows,
                                                               final int cellIndexWithContent,
                                                               final Specification specification,
                                                               final Function<Specification, String> contentExtractor) {
        final String content = contentExtractor.apply(specification);
        return findFirstRowByContent(
                rows,
                cellIndexWithContent,
                content
        );
    }

    public static OptionalInt findIndexFirstCellByContent(final XWPFTableRow row, final String content) {
        final List<XWPFTableCell> cells = row.getTableCells();
        return range(0, cells.size())
                .filter(i -> Objects.equals(content, cells.get(i).getText()))
                .findFirst();
    }

    public static double extractDoubleValue(final XWPFTableRow row, final int cellIndex) {
        return extractValue(row, cellIndex, XWPFUtil::extractDoubleValue);
    }

    public static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static boolean isCellContentMatch(final XWPFTableRow row,
                                              final int cellNumber,
                                              final String compared,
                                              final BiFunction<String, String, Boolean> matchingFunction) {
        final String actual = extractCellContent(row, cellNumber);
        return matchingFunction.apply(actual, compared);
    }

    private static String extractCellContent(final XWPFTableRow row, final int cellNumber) {
        final XWPFTableCell cell = row.getCell(cellNumber);
        final String content = cell.getText();
        return content.trim();
    }

    private static String extractContent(final XWPFTableCell cell) {
        final String content = cell.getText();
        return content.trim();
    }

    private static <V> V extractValue(final XWPFTableRow row,
                                      final int cellIndex,
                                      final Function<String, V> valueParser) {
        final String actual = extractCellContent(row, cellIndex);
        return valueParser.apply(actual);
    }

    private static double extractDoubleValue(final String content) {
        final String contentToBeParsed = content.replaceAll(COMMA, POINT);
        if (contentToBeParsed.equals("-")) {
            return Double.NaN;
        }
        return parseDouble(contentToBeParsed);
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
        return cellWithContent == null || Objects.equals(extractContent(cellWithContent), EMPTY_STRING);
    }
}