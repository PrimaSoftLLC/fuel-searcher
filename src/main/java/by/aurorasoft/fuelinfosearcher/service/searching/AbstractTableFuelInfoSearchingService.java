package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;

public abstract class AbstractTableFuelInfoSearchingService {
    private static final int INDEX_ROUTING_LENGTH_ROW = 1;

    private final FuelInfoOffsetFromRoutingLengthStorage offsetStorage;
    private final FuelTable fuelTable;

    public AbstractTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                 final FuelDocument fuelDocument,
                                                 final String fuelTableName) {
        this.offsetStorage = offsetStorage;
        this.fuelTable = findTableByName(fuelDocument, fuelTableName);
    }

    public final Optional<FuelInfo> find(final FuelInfoSpecification specification) {
        return this.findElementTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(elementTableRows -> this.findFuelInfo(elementTableRows, specification));
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    protected abstract Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                            final FuelInfoSpecification specification);

    protected abstract Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                                 final FuelInfoSpecification specification);

    private static FuelTable findTableByName(final FuelDocument fuelDocument, final String fuelTableName) {
        return fuelDocument.getTables()
                .stream()
                .filter(table -> Objects.equals(table.getName(), fuelTableName))
                .findFirst()
                .orElseThrow(
                        () -> new FuelTableNotExistException(
                                "Table '%s' doesn't exist".formatted(fuelTableName)
                        )
                );
    }

    private Optional<FuelInfo> findFuelInfo(final List<XWPFTableRow> elementTableRows,
                                            final FuelInfoSpecification specification) {
        final XWPFTableRow routingLengthRow = elementTableRows.get(INDEX_ROUTING_LENGTH_ROW);
        return this.findAppropriateRow(elementTableRows, specification)
                .flatMap(row -> this.findFuelInfoLocation(routingLengthRow, specification, row))
                .flatMap(FuelInfoUtil::extractFuelInfo);
    }

    private Optional<FuelInfoLocation> findFuelInfoLocation(final XWPFTableRow routingLengthRow,
                                                            final FuelInfoSpecification specification,
                                                            final XWPFTableRow dataRow) {
        return findIndexCellWithRoutingLength(routingLengthRow, specification)
                .stream()
                .map(cellIndexWithRoutingLength -> cellIndexWithRoutingLength + this.findFuelInfoOffset(specification))
                .mapToObj(cellIndexGenerationNorm -> createFuelInfoLocation(dataRow, cellIndexGenerationNorm))
                .findFirst();
    }

    private static OptionalInt findIndexCellWithRoutingLength(final XWPFTableRow routingLengthRow,
                                                              final FuelInfoSpecification specification) {
        final String routingLength = extractRoutingLength(specification);
        return findIndexFirstCellByContent(routingLengthRow, routingLength);
    }

    private int findFuelInfoOffset(final FuelInfoSpecification specification) {
        final String tableName = this.fuelTable.getName();
        final String routingLength = extractRoutingLength(specification);
        return this.offsetStorage.findOffset(tableName, routingLength);
    }

    private static FuelInfoLocation createFuelInfoLocation(final XWPFTableRow dataRow,
                                                           final int cellIndexGenerationNorm) {
        final int cellIndexConsumption = cellIndexGenerationNorm + 1;
        return new FuelInfoLocation(dataRow, cellIndexGenerationNorm, cellIndexConsumption);
    }
}
