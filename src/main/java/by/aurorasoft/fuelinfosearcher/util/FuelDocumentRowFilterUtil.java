package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

@UtilityClass
public final class FuelDocumentRowFilterUtil {
    //TODO: дописать все группы в комментарии
    //Группа - это удельное сопротивление для таблиц #1 и #2 и тип почвы для таблицы #3
    private static final int CELL_INDEX_GROUP_VALUE = 0;
    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_PLOUGHING_DEPTH = 4;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    public static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                 final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_TRACTOR,
                specification,
                FuelInfoSpecificationUtil::extractTractor
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                   final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_MACHINERY,
                specification,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                                      final FuelInfoSpecification specification,
                                                                      final int indexCellOfWorkingWidth) {
        return findUnitedRowsByContent(
                rows,
                indexCellOfWorkingWidth,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    public static Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                        final FuelInfoSpecification specification,
                                                        final int indexCellOfYield) {
        return findFirstRowByContent(
                rows,
                indexCellOfYield,
                specification,
                FuelInfoSpecificationUtil::extractYield
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_CORPUS_COUNT,
                specification,
                FuelInfoSpecificationUtil::extractCorpusCount
        );
    }

    public static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                 final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_PLOUGHING_DEPTH,
                specification,
                FuelInfoSpecificationUtil::extractPloughingDepth
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                                    final String groupValue,
                                                                    final String groupValueRegex) {
        return findIndexRowByGroupValue(rows, groupValue)
                .stream()
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingGroupValue(indexFirstMatchingRow, rows, groupValueRegex))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    public static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                               final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_WORKING_WIDTH,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    private static OptionalInt findIndexRowByGroupValue(final List<XWPFTableRow> rows, final String groupValue) {
        return findIndexFirstRowByContent(
                rows,
                CELL_INDEX_GROUP_VALUE,
                groupValue
        );
    }

    private static IntPair findIndexBordersRowsMatchingGroupValue(final int indexFirstMatchingRow,
                                                                  final List<XWPFTableRow> rows,
                                                                  final String groupValueRegex) {
        final int nextIndexLastMatchingRow = findIndexRowNextGroupValueOrLastRow(
                rows, indexFirstMatchingRow, groupValueRegex
        );
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextGroupValueOrLastRow(final List<XWPFTableRow> rows,
                                                           final int startSearchingIndex,
                                                           final String groupValueRegex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_GROUP_VALUE,
                groupValueRegex
        ).orElse(rows.size());
    }

}
