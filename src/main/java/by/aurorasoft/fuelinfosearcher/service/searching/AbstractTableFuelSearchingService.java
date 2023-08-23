package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

//TODO: put FuelInfoLocation as inner class
public abstract class AbstractTableFuelSearchingService {
    private static final int ROW_INDEX_FUEL_HEADER = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaders;

    public AbstractTableFuelSearchingService(final FuelDocument fuelDocument,
                                             final String fuelTableName,
                                             final String[] fuelHeaders) {
        this.fuelTable = findTableByName(fuelDocument, fuelTableName);
        this.fuelOffsetsByHeaders = createFuelOffsetsByHeaders(fuelHeaders);
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    public final Optional<Fuel> find(final FuelSpecification specification) {
        return this.findElementTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(elementTableRows -> this.findFuel(elementTableRows, specification));
    }

    protected abstract Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                            final FuelSpecification specification);

    protected abstract RowFilterChain createRowFilterChain();

    protected abstract String extractFuelHeaderCellValue(final FuelSpecification specification);

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

    private static Map<String, Integer> createFuelOffsetsByHeaders(final String[] fuelHeaders) {
        return range(0, fuelHeaders.length)
                .boxed()
                .collect(toMap(i -> fuelHeaders[i], identity()));
    }

    private Optional<Fuel> findFuel(final List<XWPFTableRow> elementTableRows, final FuelSpecification specification) {
        final XWPFTableRow fuelHeaderRow = elementTableRows.get(ROW_INDEX_FUEL_HEADER);
        return this.findAppropriateRow(elementTableRows, specification)
                .flatMap(row -> this.findFuelLocation(fuelHeaderRow, specification, row))
                .flatMap(FuelUtil::extractFuel);
    }

    private Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                      final FuelSpecification specification) {
        final RowFilterChain filterChain = this.createRowFilterChain();
        return filterChain.filter(elementTableRows, specification);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow fuelHeaderRow,
                                                    final FuelSpecification specification,
                                                    final XWPFTableRow dataRow) {
        final String fuelHeaderCellValue = this.extractFuelHeaderCellValue(specification);
        return findIndexFirstCellByContent(fuelHeaderRow, fuelHeaderCellValue)
                .stream()
                .map(fuelHeaderCellIndex -> this.findCellIndexGenerationNorm(fuelHeaderCellIndex, fuelHeaderCellValue))
                .mapToObj(generationNormCellIndex -> createFuelLocation(dataRow, generationNormCellIndex))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int fuelHeaderCellIndex, final String fuelHeaderCellValue) {
        final int fuelOffset = this.fuelOffsetsByHeaders.get(fuelHeaderCellValue);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private static FuelLocation createFuelLocation(final XWPFTableRow dataRow, final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelLocation(dataRow, generationNormCellIndex, consumptionCellIndex);
    }
}
