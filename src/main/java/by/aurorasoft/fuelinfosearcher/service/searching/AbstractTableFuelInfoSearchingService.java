package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.*;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

public abstract class AbstractTableFuelInfoSearchingService {
    private static final int INDEX_ROUTING_LENGTH_ROW = 1;

    private final FuelTable fuelTable;

    //cell's indexes of generation norm by routing lengths
    //TODO: rename
    private final Map<String, Integer> fuelInfoOffsetsByRoutingLengths;

    public AbstractTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                 final String fuelTableName,
                                                 final String[] routingLengths,
                                                 final int firstFuelInfoOffset) {
        this.fuelTable = findTableByName(fuelDocument, fuelTableName);
        this.fuelInfoOffsetsByRoutingLengths = createFuelInfoOffsetsByRoutingLengths(
                routingLengths, firstFuelInfoOffset
        );
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    public final Optional<FuelInfo> find(final FuelInfoSpecification specification) {
        return this.findElementTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(elementTableRows -> this.findFuelInfo(elementTableRows, specification));
    }

    protected abstract Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                            final FuelInfoSpecification specification);

    protected abstract Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                                 final FuelInfoSpecification specification);

    //к этому прибавляется offset
    protected abstract OptionalInt findFuelInfoCellIndex(final FuelInfoSpecification specification);

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

    private static Map<String, Integer> createFuelInfoOffsetsByRoutingLengths(final String[] routingLengths,
                                                                              final int firstFuelInfoOffset) {
        return range(0, routingLengths.length)
                .boxed()
                .collect(toMap(i -> routingLengths[i], i -> i + firstFuelInfoOffset));
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
        final String routingLength = extractRoutingLength(specification);
        return findIndexFirstCellByContent(routingLengthRow, routingLength)
                .stream()
                .map(cellIndexRoutingLength -> this.findCellIndexGenerationNorm(cellIndexRoutingLength, routingLength))
                .mapToObj(cellIndexGenerationNorm -> createFuelInfoLocation(dataRow, cellIndexGenerationNorm))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int cellIndexRoutingLength, final String routingLength) {
        final int fuelInfoOffset = this.fuelInfoOffsetsByRoutingLengths.get(routingLength);
        return cellIndexRoutingLength + fuelInfoOffset;
    }

    private static FuelInfoLocation createFuelInfoLocation(final XWPFTableRow dataRow,
                                                           final int cellIndexGenerationNorm) {
        final int cellIndexConsumption = cellIndexGenerationNorm + 1;
        return new FuelInfoLocation(dataRow, cellIndexGenerationNorm, cellIndexConsumption);
    }
}
