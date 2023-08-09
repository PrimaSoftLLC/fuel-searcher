package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;
import static java.util.stream.IntStream.iterate;

@Service
public final class TwelfthTableFuelInfoSearchingService extends AbstractTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY = "Опрыскивателем %s";

    private static final int INDEX_ROUTING_LENGTH_ROW = 1;

    private static final int CELL_INDEX_WITH_FERTILIZER_TYPE = 0;
    private static final String REGEX_CONTENT_OF_FERTILIZER_TYPE = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";

    private static final int CELL_INDEX_WITH_CHARGING_METHOD_AND_TRANSPORT_DISTANCE = 1;
    private static final int CELL_INDEX_WITH_SPREAD_RATE = 2;

    public TwelfthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

    @Override
    protected Optional<FuelInfo> find(final FuelTable fuelTable, final FuelInfoSpecification specification) {
        final Optional<List<XWPFTableRow>> optionalRows = extractElementTableRows(fuelTable, specification);
        if (optionalRows.isEmpty()) {
            return Optional.empty();
        }
        final List<XWPFTableRow> elementTableRows = optionalRows.get();
        final XWPFTableRow routingLengthRow = elementTableRows.get(INDEX_ROUTING_LENGTH_ROW);
        Optional<FuelInfo> fuelInfo = findRowsByFertilizerType(elementTableRows, specification)
                .flatMap(rows -> findRowsByChargingMethodAndTransportDistance(rows, specification))
                .flatMap(rows -> findRowBySpreadRate(rows, specification))
                .flatMap(row -> this.findFuelInfo(routingLengthRow, row, specification));
        return fuelInfo;
    }

    private static Optional<List<XWPFTableRow>> extractElementTableRows(final FuelTable fuelTable, final FuelInfoSpecification specification) {
        final Optional<XWPFTable> elementTable = extractElementTable(fuelTable, specification);
        return elementTable.map(XWPFTable::getRows);
    }

    private static Optional<XWPFTable> extractElementTable(final FuelTable fuelTable, final FuelInfoSpecification specification) {
        final OptionalInt optionalFoundParagraphIndex = findIndexParagraphByMachinery(fuelTable, specification);
        if (optionalFoundParagraphIndex.isEmpty()) {
            return Optional.empty();
        }
        final int foundParagraphIndex = optionalFoundParagraphIndex.getAsInt();
        final int elementTableIndex = foundParagraphIndex + 1;
        return Optional.of((XWPFTable) fuelTable.getElements().get(elementTableIndex));
    }

    private static OptionalInt findIndexParagraphByMachinery(final FuelTable fuelTable,
                                                             final FuelInfoSpecification specification) {
        final String machinery = extractMachinery(specification);
        final String contentOfResultParagraph = TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY.formatted(machinery);
        final List<IBodyElement> elements = fuelTable.getElements();
        //предполагается что 1 элемент - параграф, 2 - таблица и т.д.
        return iterate(0, i -> i < elements.size(), i -> i + 2)
//                .peek(i -> System.out.println(((XWPFParagraph) elements.get(i)).getText()))
                .filter(i -> ((XWPFParagraph) elements.get(i)).getText().equals(contentOfResultParagraph))
                .findFirst();
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

    private Optional<FuelInfo> findFuelInfo(final XWPFTableRow routingLengthRow,
                                            final XWPFTableRow dataRow,
                                            final FuelInfoSpecification specification) {
        final Optional<FuelInfoLocation> optionalLocation = this.findFuelInfoLocation(
                routingLengthRow, specification, dataRow
        );
        Optional<FuelInfo> fuelInfo = optionalLocation.flatMap(FuelInfoUtil::extractFuelInfo);
        return fuelInfo;
    }

    private Optional<FuelInfoLocation> findFuelInfoLocation(final XWPFTableRow routingLengthRow,
                                                            final FuelInfoSpecification specification,
                                                            final XWPFTableRow dataRow) {
        return findIndexCellWithRoutingLength(routingLengthRow, specification)
                .stream()
                .map(cellIndexWithRoutingLength -> cellIndexWithRoutingLength + super.findFuelInfoOffset(specification))
                .mapToObj(cellIndexGenerationNorm -> createFuelInfoLocation(dataRow, cellIndexGenerationNorm))
                .findFirst();
    }

    private static OptionalInt findIndexCellWithRoutingLength(final XWPFTableRow routingLengthRow,
                                                              final FuelInfoSpecification specification) {
        final String routingLength = extractRoutingLength(specification);
        return findIndexFirstCellByContent(routingLengthRow, routingLength);
    }

    private static FuelInfoLocation createFuelInfoLocation(final XWPFTableRow dataRow,
                                                           final int cellIndexGenerationNorm) {
        final int cellIndexConsumption = cellIndexGenerationNorm + 1;
        return new FuelInfoLocation(dataRow, cellIndexGenerationNorm, cellIndexConsumption);
    }
}
