package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

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
    private static final int CELL_INDEX_CHARGING_METHOD_AND_TRANSPORT_DISTANCE = 1;
    private static final int CELL_INDEX_WITH_SPREAD_RATE = 2;

    private static final String REGEX_CONTENT_PROCESSING_DEPTH = "Глубина обработки \\d+((…)|(...))\\d+ см";
    private static final String REGEX_SOIL_TYPE_CONTENT = "(Минеральные почвы)|(Торфяные почвы)";
    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
    private static final String REGEX_CONTENT_SOWING_NORM = "Норма высева (семян )?\\d+(–\\d+)? кг/га";
    private static final String REGEX_CONTENT_FERTILIZER_TYPE = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";


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

    public static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                               final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_WORKING_WIDTH,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByProcessingDepth(final List<XWPFTableRow> rows,
                                                                         final FuelInfoSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractProcessingDepth,
                REGEX_CONTENT_PROCESSING_DEPTH
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsBySoilType(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSoilType,
                REGEX_SOIL_TYPE_CONTENT
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsBySpecificResistance(final List<XWPFTableRow> rows,
                                                                            final FuelInfoSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSpecificResistance,
                REGEX_CONTENT_SPECIFIC_RESISTANCE
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsBySowingNorm(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSowingNorm,
                REGEX_CONTENT_SOWING_NORM
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByFertilizerType(final List<XWPFTableRow> rows,
                                                                        final FuelInfoSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractFertilizerType,
                REGEX_CONTENT_FERTILIZER_TYPE
        );
    }

    public static Optional<List<XWPFTableRow>> findRowsByChargingMethodAndTransportDistance(final List<XWPFTableRow> rows,
                                                                                            final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_CHARGING_METHOD_AND_TRANSPORT_DISTANCE,
                specification,
                FuelInfoSpecificationUtil::extractChargingMethodAndTransportDistance
        );
    }

    public static Optional<XWPFTableRow> findRowBySpreadRate(final List<XWPFTableRow> rows,
                                                             final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_WITH_SPREAD_RATE,
                specification,
                FuelInfoSpecificationUtil::extractSpreadRate
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification,
                                                                     final Function<FuelInfoSpecification, String> groupValueExtractor,
                                                                     final String groupValueRegex) {
        final String groupValue = groupValueExtractor.apply(specification);
        return findIndexRowByGroupValue(rows, groupValue)
                .stream()
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingGroupValue(indexFirstMatchingRow, rows, groupValueRegex))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
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
