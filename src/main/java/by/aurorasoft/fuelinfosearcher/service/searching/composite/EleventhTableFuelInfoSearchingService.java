package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

@Service
public final class EleventhTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ";
    private static final String[] ROUTING_LENGTHS = new String[]{
            "Менее 150", "150–200", "201–300", "301–400", "401–600", "601–1000", "Более 1000"
    };
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY_AND_TRACTOR = "РАЗБРАСЫВАТЕЛЕМ %s (трактор %s)";

    private static final int CELL_INDEX_WITH_FERTILIZER_TYPE = 0;
    private static final String REGEX_CONTENT_OF_FERTILIZER_TYPE = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";

    private static final int CELL_INDEX_WITH_CHARGING_METHOD_AND_TRANSPORT_DISTANCE = 1;
    private static final int CELL_INDEX_WITH_SPREAD_RATE = 2;


    public EleventhTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows, final FuelInfoSpecification specification) {
        return findRowsByFertilizerType(elementTableRows, specification)
                .flatMap(rows -> findRowsByChargingMethodAndTransportDistance(rows, specification))
                .flatMap(rows -> findRowBySpreadRate(rows, specification));
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY_AND_TRACTOR;
    }

    @Override
    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractMachinery,
                FuelInfoSpecificationUtil::extractTractor
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByFertilizerType(final List<XWPFTableRow> rows,
                                                                         final FuelInfoSpecification specification) {
        Optional<List<XWPFTableRow>> first = findIndexRowByFertilizerType(rows, specification)
                .stream()
                .map(indexRowWithFertilizerType -> indexRowWithFertilizerType + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingFertilizerType(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
        return first;
    }

    private static OptionalInt findIndexRowByFertilizerType(final List<XWPFTableRow> rows,
                                                            final FuelInfoSpecification specification) {
        final String fertilizerType = extractFertilizerType(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_FERTILIZER_TYPE, fertilizerType);
    }

    private static IntPair findIndexBordersRowsMatchingFertilizerType(final int indexFirstMatchingRow,
                                                                      final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextFertilizerTypeOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextFertilizerTypeOrLastRow(final List<XWPFTableRow> rows,
                                                               final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_WITH_FERTILIZER_TYPE,
                REGEX_CONTENT_OF_FERTILIZER_TYPE
        ).orElse(rows.size());
    }

    private static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static Optional<List<XWPFTableRow>> findRowsByChargingMethodAndTransportDistance(final List<XWPFTableRow> rows,
                                                                                             final FuelInfoSpecification specification) {
        //TODO: remove variable
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_CHARGING_METHOD_AND_TRANSPORT_DISTANCE,
                extractChargingMethodAndTransportDistance(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<XWPFTableRow> findRowBySpreadRate(final List<XWPFTableRow> rows,
                                                              final FuelInfoSpecification specification) {
        final String spreadRate = extractSpreadRate(specification);
        Optional<XWPFTableRow> firstRowByContent = findFirstRowByContent(rows, CELL_INDEX_WITH_SPREAD_RATE, spreadRate);
        return firstRowByContent;
    }
}
