package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

//TODO: refactor
//TODO: возможно стоит вообще все индексы хранить в срвисах а не здесь
@UtilityClass
public final class FuelDocumentRowFilterUtil {
    //TODO: дописать все группы в комментарии
    //Группа - это удельное сопротивление для таблиц #1 и #2 и тип почвы для таблицы #3
    private static final int CELL_INDEX_GROUP_VALUE = 0;

    private static final String REGEX_CONTENT_PROCESSING_DEPTH = "Глубина обработки \\d+...\\d+ см";
    private static final String REGEX_SOIL_TYPE_CONTENT = "(Минеральные почвы)|(Торфяные почвы)|(Легкие почвы)|(Средние почвы)|(Тяжелые почвы)";
    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";
    private static final String REGEX_CONTENT_SOWING_NORM = "Норма высева (семян )?\\d+(-\\d+)? кг/га";
    private static final String REGEX_CONTENT_FERTILIZER_TYPE = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";
    private static final String REGEX_CONTENT_CARGO_CLASS = "Грузы (I|II|III|IV) класса";
    private static final String REGEX_CONTENT_ROAD_GROUP = "((Первая)|(Вторая)|(Третья)) группа дорог";


    public static List<XWPFTableRow> findRowsByTractor(final List<XWPFTableRow> rows,
                                                       final FuelSpecification specification,
                                                       final int cellIndexTractor) {
        return findUnitedRowsByContent(
                rows,
                cellIndexTractor,
                specification,
                FuelInfoSpecificationUtil::extractTractor
        );
    }

    public static List<XWPFTableRow> findRowsByCombine(final List<XWPFTableRow> rows,
                                                       final FuelSpecification specification,
                                                       final int cellIndexCombine) {
        return findUnitedRowsByContent(
                rows,
                cellIndexCombine,
                specification,
                FuelInfoSpecificationUtil::extractCombine
        );
    }

    public static List<XWPFTableRow> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                         final FuelSpecification specification,
                                                         final int cellIndexMachinery) {
        return findUnitedRowsByContent(
                rows,
                cellIndexMachinery,
                specification,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    public static List<XWPFTableRow> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                            final FuelSpecification specification,
                                                            final int cellIndexWorkingWidth) {
        return findUnitedRowsByContent(
                rows,
                cellIndexWorkingWidth,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    public static Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification,
                                                        final int cellIndexYield) {
        return findFirstRowByContent(
                rows,
                cellIndexYield,
                specification,
                FuelInfoSpecificationUtil::extractYield
        );
    }

    public static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                               final FuelSpecification specification,
                                                               final int cellIndexWorkingWidth) {
        return findFirstRowByContent(
                rows,
                cellIndexWorkingWidth,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    public static List<XWPFTableRow> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                     final FuelSpecification specification,
                                                                     final int cellIndexCorpusCount) {
        return findUnitedRowsByContent(
                rows,
                cellIndexCorpusCount,
                specification,
                FuelInfoSpecificationUtil::extractCorpusCount
        );
    }

    public static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                 final FuelSpecification specification,
                                                                 final int cellIndexPloughingDepth) {
        return findFirstRowByContent(
                rows,
                cellIndexPloughingDepth,
                specification,
                FuelInfoSpecificationUtil::extractPloughingDepth
        );
    }

    public static Optional<XWPFTableRow> findRowByTransportDistance(final List<XWPFTableRow> rows,
                                                                    final FuelSpecification specification,
                                                                    final int cellIndexTransportDistance) {
        return findFirstRowByContent(
                rows,
                cellIndexTransportDistance,
                specification,
                FuelInfoSpecificationUtil::extractTransportDistance
        );
    }

    public static List<XWPFTableRow> findRowsByProcessingDepth(final List<XWPFTableRow> rows,
                                                                         final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractProcessingDepth,
                REGEX_CONTENT_PROCESSING_DEPTH
        );
    }

    public static List<XWPFTableRow> findRowsBySoilType(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSoilType,
                REGEX_SOIL_TYPE_CONTENT
        );
    }

    public static List<XWPFTableRow> findRowsBySpecificResistance(final List<XWPFTableRow> rows,
                                                                            final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSpecificResistance,
                REGEX_CONTENT_SPECIFIC_RESISTANCE
        );
    }

    public static List<XWPFTableRow> findRowsBySowingNorm(final List<XWPFTableRow> rows,
                                                                    final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractSowingNorm,
                REGEX_CONTENT_SOWING_NORM
        );
    }

    public static List<XWPFTableRow> findRowsByFertilizerType(final List<XWPFTableRow> rows,
                                                              final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractFertilizerType,
                REGEX_CONTENT_FERTILIZER_TYPE
        );
    }

    public static List<XWPFTableRow> findRowsByCargoClass(final List<XWPFTableRow> rows,
                                                          final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractCargoClass,
                REGEX_CONTENT_CARGO_CLASS
        );
    }

    public static List<XWPFTableRow> findRowsByRoadGroup(final List<XWPFTableRow> rows,
                                                         final FuelSpecification specification) {
        return findRowsByGroupValue(
                rows,
                specification,
                FuelInfoSpecificationUtil::extractRoadGroup,
                REGEX_CONTENT_ROAD_GROUP
        );
    }

    public static List<XWPFTableRow> findRowsByChargingMethodAndTransportDistance(final List<XWPFTableRow> rows,
                                                                                  final FuelSpecification specification,
                                                                                  final int cellIndexChargingMethodAndTransportDistance) {
        return findUnitedRowsByContent(
                rows,
                cellIndexChargingMethodAndTransportDistance,
                specification,
                FuelInfoSpecificationUtil::extractChargingMethodAndTransportDistance
        );
    }

    public static Optional<XWPFTableRow> findRowBySpreadRate(final List<XWPFTableRow> rows,
                                                             final FuelSpecification specification,
                                                             final int cellIndexSpreadRate) {
        return findFirstRowByContent(
                rows,
                cellIndexSpreadRate,
                specification,
                FuelInfoSpecificationUtil::extractSpreadRate
        );
    }

    public static List<XWPFTableRow> findRowsByRowWidth(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification,
                                                        final int cellIndexRowWidth) {
        return findUnitedRowsByContent(
                rows,
                cellIndexRowWidth,
                specification,
                FuelInfoSpecificationUtil::extractRowWidth
        );
    }


    private static List<XWPFTableRow> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                                     final FuelSpecification specification,
                                                                     final Function<FuelSpecification, String> groupValueExtractor,
                                                                     final String groupValueRegex) {
        final String groupValue = groupValueExtractor.apply(specification);
        return findRowIndexesByContent(rows, CELL_INDEX_GROUP_VALUE, groupValue)
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingGroupValue(indexFirstMatchingRow, rows, groupValueRegex))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .flatMap(Collection::stream)
                .toList();
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
