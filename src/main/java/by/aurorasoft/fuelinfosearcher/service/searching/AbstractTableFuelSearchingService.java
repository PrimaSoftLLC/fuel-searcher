package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.AbstractConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.AbstractIntermediateRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.conclusive.TEMPConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.start.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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

    protected abstract Stream<AbstractIntermediateRowFilter> createIntermediateRowFilters();
    protected abstract AbstractConclusiveRowFilter createConclusiveRowFilter();

//    protected abstract Stream<StartRowFilter> createStartRowFilters();
//
//    protected abstract TEMPConclusiveRowFilter createFinalRowFilter();

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
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> rowFilter = this.createRowFilter(specification);
        return rowFilter.apply(elementTableRows);
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createRowFilter(final FuelSpecification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> finalRowFilter = this.createFinalRowFilter(specification);
        return this.createStartRowFilters(specification)
                .reduce(Function::andThen)
                .map(rowFilter -> rowFilter.andThen(finalRowFilter))
                .orElse(finalRowFilter);
    }

    private Stream<Function<List<XWPFTableRow>, List<XWPFTableRow>>> createStartRowFilters(final FuelSpecification specification) {
        final Stream<StartRowFilter> startRowFilters = this.createStartRowFilters();
        return startRowFilters.map(startRowFilter -> startRowFilter.createFunctionRowFilter(specification));
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFinalRowFilter(final FuelSpecification specification) {
        final TEMPConclusiveRowFilter finalRowFilter = this.createFinalRowFilter();
        return finalRowFilter.createFunctionRowFilter(specification);
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
