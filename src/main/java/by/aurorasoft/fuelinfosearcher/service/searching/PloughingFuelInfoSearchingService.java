package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelInfoSearchingException;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil.extractFuelInfo;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;
import static java.util.Optional.empty;

@Service
public final class PloughingFuelInfoSearchingService extends AbstractFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    private static final int CELL_INDEX_WITH_SPECIFIC_RESISTANCE = 0;
    private static final int CELL_INDEX_WITH_TRACTOR = 1;
    private static final int CELL_INDEX_WITH_PLOUGH_MARK = 2;
    private static final int CELL_INDEX_WITH_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_WITH_PLOUGHING_DEPTH = 4;

    private static final int UNITED_ROWS_COUNT_IN_TRACTOR_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_PLOUGH_MARK_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_CORPUS_COUNT_COLUMN = 4;

    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    private static final int ROW_INDEX_WITH_ROUTING_LENGTHS = 1;

    public PloughingFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                             final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

    @Override
    protected Optional<FuelInfo> find(final FuelTable fuelTable, final FuelInfoSpecification specification) {
        return extractElementTable(fuelTable)
                .map(XWPFTable::getRows)
                .flatMap(rows -> findRowsBySpecificResistance(rows, specification))
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByPloughMark(rows, specification))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification))
                .map(row -> this.findFuelInfo(null, row, specification));

        final XWPFTable elementTable = extractElementTable(fuelTable);
        final List<XWPFTableRow> allRows = elementTable.getRows();


        final Optional<List<XWPFTableRow>> optionalRowsFoundBySpecificResistance = findRowsBySpecificResistance(
                allRows, specification
        );
        if (optionalRowsFoundBySpecificResistance.isEmpty()) {
            return empty();
        }
        final List<XWPFTableRow> rowsFoundBySpecificResistance = optionalRowsFoundBySpecificResistance.get();
        final List<XWPFTableRow> rowsFoundByTractor = findRowsByTractor(rowsFoundBySpecificResistance, specification);
        final List<XWPFTableRow> rowsFoundByPloughMark = findRowsByPloughMark(rowsFoundByTractor, specification);
        final List<XWPFTableRow> rowsFoundByCorpusCount = findRowsByCorpusCount(rowsFoundByPloughMark, specification);
        final XWPFTableRow rowFoundByPloughingDepth = findRowByPloughingDepth(rowsFoundByCorpusCount, specification);

        final XWPFTableRow rowWithRoutingLengths = allRows.get(ROW_INDEX_WITH_ROUTING_LENGTHS);
        return this.findFuelInfo(rowWithRoutingLengths, rowFoundByPloughingDepth, specification);
    }

    private static Optional<XWPFTable> extractElementTable(final FuelTable fuelTable) {
        final List<IBodyElement> elements = fuelTable.getElements();
        return (elements.isEmpty() || !(elements.get(0) instanceof final XWPFTable table)) ? empty() : Optional.of(table);
    }

    private static Optional<List<XWPFTableRow>> findRowsBySpecificResistance(final List<XWPFTableRow> rows,
                                                                             final FuelInfoSpecification specification) {
        final OptionalInt optionalIndexRowWithGivenSpecificResistance = findIndexRowWithGivenSpecificResistance(
                rows, specification
        );
        if (optionalIndexRowWithGivenSpecificResistance.isEmpty()) {
            return empty();
        }
        final int indexFirstMatchingRow = optionalIndexRowWithGivenSpecificResistance.getAsInt() + 1;
        final int nextIndexLastMatchingRow = findIndexFirstRowByContentRegex(
                rows, indexFirstMatchingRow, CELL_INDEX_WITH_SPECIFIC_RESISTANCE, REGEX_CONTENT_SPECIFIC_RESISTANCE
        ).orElse(rows.size());
        return Optional.of(rows.subList(indexFirstMatchingRow, nextIndexLastMatchingRow));
    }

    private static OptionalInt findIndexRowWithGivenSpecificResistance(final List<XWPFTableRow> rows,
                                                                       final FuelInfoSpecification specification) {
        final String specificResistance = extractSpecificResistance(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_SPECIFIC_RESISTANCE, specificResistance);
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
        return findFirstRowByContent(rows, CELL_INDEX_WITH_PLOUGHING_DEPTH, ploughingDepth)
                .orElseThrow(
                        () -> new FuelInfoSearchingException(
                                "There is no row with ploughing depth '%s'".formatted(ploughingDepth)
                        )
                );
    }

    private FuelInfo findFuelInfo(final XWPFTableRow routingLengthRow,
                                            final XWPFTableRow dataRow,
                                            final FuelInfoSpecification specification) {
        final FuelInfoLocation location = this.findFuelInfoLocation(routingLengthRow, specification, dataRow);
        return extractFuelInfo(location);
    }

    private FuelInfoLocation findFuelInfoLocation(final XWPFTableRow routingLengthRow,
                                                  final FuelInfoSpecification specification,
                                                  final XWPFTableRow dataRow) {
        final int cellIndexWithRoutingLength = findIndexCellWithRoutingLength(routingLengthRow, specification);
        final int cellIndexGenerationNorm = cellIndexWithRoutingLength + super.findFuelInfoOffset(specification);
        final int cellIndexConsumption = cellIndexGenerationNorm + 1;
        return new FuelInfoLocation(dataRow, cellIndexGenerationNorm, cellIndexConsumption);
    }

    private static int findIndexCellWithRoutingLength(final XWPFTableRow routingLengthRow,
                                                      final FuelInfoSpecification specification) {
        final String routingLength = extractRoutingLength(specification);
        return findIndexFirstCellByContent(routingLengthRow, routingLength)
                .orElseThrow(
                        () -> new FuelInfoSearchingException(
                                "There is no cell with given routing length: %s".formatted(routingLength)
                        )
                );
    }
}
