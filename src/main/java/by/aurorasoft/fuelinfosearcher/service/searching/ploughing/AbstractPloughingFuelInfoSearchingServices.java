package by.aurorasoft.fuelinfosearcher.service.searching.ploughing;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;

//for tables #1, #2, #3
public abstract class AbstractPloughingFuelInfoSearchingServices extends AbstractFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_SPECIFIC_RESISTANCE = 0;
    private static final int CELL_INDEX_WITH_TRACTOR = 1;
    private static final int CELL_INDEX_WITH_PLOUGH_MARK = 2;
    private static final int CELL_INDEX_WITH_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_WITH_PLOUGHING_DEPTH = 4;

    private static final int UNITED_ROWS_COUNT_IN_TRACTOR_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_PLOUGH_MARK_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_CORPUS_COUNT_COLUMN = 4;

    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    private static final int INDEX_ROUTING_LENGTH_ROW = 1;

    public AbstractPloughingFuelInfoSearchingServices(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                      final FuelDocument fuelDocument,
                                                      final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }

    @Override
    protected final Optional<FuelInfo> find(final FuelTable fuelTable, final FuelInfoSpecification specification) {
        final List<XWPFTableRow> elementTableRows = extractElementTableRows(fuelTable);
        final XWPFTableRow routingLengthRow = elementTableRows.get(INDEX_ROUTING_LENGTH_ROW);
        return findRowsBySpecificResistance(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByPloughMark(rows, specification))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification))
                .flatMap(row -> this.findFuelInfo(routingLengthRow, row, specification));
    }

    private static List<XWPFTableRow> extractElementTableRows(final FuelTable fuelTable) {
        final XWPFTable elementTable = extractElementTable(fuelTable);
        return elementTable.getRows();
    }

    private static XWPFTable extractElementTable(final FuelTable fuelTable) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        return (XWPFTable) firstElement;
    }

    private static Optional<List<XWPFTableRow>> findRowsBySpecificResistance(final List<XWPFTableRow> rows,
                                                                             final FuelInfoSpecification specification) {
        return findIndexRowBySpecificResistance(rows, specification)
                .stream()
                .map(indexRowWithSpecificResistance -> indexRowWithSpecificResistance + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingSpecificResistance(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private static OptionalInt findIndexRowBySpecificResistance(final List<XWPFTableRow> rows,
                                                                final FuelInfoSpecification specification) {
        final String specificResistance = extractSpecificResistance(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_SPECIFIC_RESISTANCE, specificResistance);
    }

    private static IntPair findIndexBordersRowsMatchingSpecificResistance(final int indexFirstMatchingRow,
                                                                          final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextSpecificResistanceOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextSpecificResistanceOrLastRow(final List<XWPFTableRow> rows,
                                                                   final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_WITH_SPECIFIC_RESISTANCE,
                REGEX_CONTENT_SPECIFIC_RESISTANCE
        ).orElse(rows.size());
    }

    private static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_TRACTOR,
                UNITED_ROWS_COUNT_IN_TRACTOR_COLUMN,
                extractTractor(specification)
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByPloughMark(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_PLOUGH_MARK,
                UNITED_ROWS_COUNT_IN_PLOUGH_MARK_COLUMN,
                extractPloughMark(specification)
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                      final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_CORPUS_COUNT,
                UNITED_ROWS_COUNT_IN_CORPUS_COUNT_COLUMN,
                extractCorpusCount(specification)
        );
    }

    private static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        final String ploughingDepth = extractPloughingDepth(specification);
        return findFirstRowByContent(rows, CELL_INDEX_WITH_PLOUGHING_DEPTH, ploughingDepth);
    }

    private Optional<FuelInfo> findFuelInfo(final XWPFTableRow routingLengthRow,
                                            final XWPFTableRow dataRow,
                                            final FuelInfoSpecification specification) {
        final Optional<FuelInfoLocation> optionalLocation = this.findFuelInfoLocation(
                routingLengthRow, specification, dataRow
        );
        return optionalLocation.flatMap(FuelInfoUtil::extractFuelInfo);
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
