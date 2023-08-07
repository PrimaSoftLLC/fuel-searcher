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
    private static final int CELL_INDEX_WITH_GROUP_VALUE = 0;
    private static final int CELL_INDEX_WITH_TRACTOR = 1;
    private static final int CELL_INDEX_WITH_MACHINERY = 2;
    private static final int CELL_INDEX_WITH_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_WITH_PLOUGHING_DEPTH = 4;

    private static final int UNITED_ROWS_COUNT_IN_TRACTOR_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_MACHINERY_COLUMN = 4;
    private static final int UNITED_ROWS_COUNT_IN_CORPUS_COUNT_COLUMN = 4;

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
        return this.findRowsByGroupValue(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification))
                .flatMap(row -> this.findFuelInfo(routingLengthRow, row, specification));
    }

    //Группа - это удельное сопротивление для таблиц #1 и #2 и тип почвы для таблицы #3
    protected abstract String extractGroupValue(final FuelInfoSpecification specification);
    protected abstract String findGroupValueRegex();


    private static List<XWPFTableRow> extractElementTableRows(final FuelTable fuelTable) {
        final XWPFTable elementTable = extractElementTable(fuelTable);
        return elementTable.getRows();
    }

    private static XWPFTable extractElementTable(final FuelTable fuelTable) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        return (XWPFTable) firstElement;
    }

    private Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                              final FuelInfoSpecification specification) {
        return findIndexRowByGroupValue(rows, specification)
                .stream()
                .map(indexRowWithGroupValue -> indexRowWithGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> this.findIndexBordersRowsMatchingSpecificResistance(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private OptionalInt findIndexRowByGroupValue(final List<XWPFTableRow> rows,
                                                 final FuelInfoSpecification specification) {
        final String groupValue = this.extractGroupValue(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_GROUP_VALUE, groupValue);
    }

    private IntPair findIndexBordersRowsMatchingSpecificResistance(final int indexFirstMatchingRow,
                                                                   final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextSpecificResistanceOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private int findIndexRowNextSpecificResistanceOrLastRow(final List<XWPFTableRow> rows,
                                                            final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_WITH_GROUP_VALUE,
                this.findGroupValueRegex()
        ).orElse(rows.size());
    }

    private static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        //TODO: remove variable
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_TRACTOR,
                extractTractor(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        //TODO: remove variable
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_MACHINERY,
                extractMachinery(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<List<XWPFTableRow>> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                      final FuelInfoSpecification specification) {
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_CORPUS_COUNT,
                extractCorpusCount(specification)
        );
        return unitedRowsByContent;
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
