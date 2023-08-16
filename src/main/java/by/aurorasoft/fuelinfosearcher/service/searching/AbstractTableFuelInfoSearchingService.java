package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

//TODO: put FuelInfoLocation as inner class
public abstract class AbstractTableFuelInfoSearchingService {
    private static final int INDEX_ROW_FUEL_INFO_HEADER = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelInfoOffsetsByHeaders;

    public AbstractTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                 final String fuelTableName,
                                                 final String[] fuelInfoHeaders) {
        this.fuelTable = findTableByName(fuelDocument, fuelTableName);
        this.fuelInfoOffsetsByHeaders = createFuelInfoOffsetsByHeaders(fuelInfoHeaders);
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

    protected abstract Stream<BiFunction<List<XWPFTableRow>, FuelInfoSpecification, List<XWPFTableRow>>> createStartRowFilters();

    protected abstract BiFunction<List<XWPFTableRow>, FuelInfoSpecification, Optional<XWPFTableRow>> createFinalRowFilter();

    protected abstract String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification);

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

    private static Map<String, Integer> createFuelInfoOffsetsByHeaders(final String[] fuelInfoHeaders) {
        return range(0, fuelInfoHeaders.length)
                .boxed()
                .collect(toMap(i -> fuelInfoHeaders[i], identity()));
    }

    private Optional<FuelInfo> findFuelInfo(final List<XWPFTableRow> elementTableRows,
                                            final FuelInfoSpecification specification) {
        final XWPFTableRow fuelInfoHeaderRow = elementTableRows.get(INDEX_ROW_FUEL_INFO_HEADER);
        return this.findAppropriateRow(elementTableRows, specification)
                .flatMap(row -> this.findFuelInfoLocation(fuelInfoHeaderRow, specification, row))
                .flatMap(FuelInfoUtil::extractFuelInfo);
    }

    private Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                      final FuelInfoSpecification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> filteringFunction = this.createFilteringFunction(
                specification
        );
        return filteringFunction.apply(elementTableRows);
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFilteringFunction(
            final FuelInfoSpecification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> finalRowFilter = this.createFinalRowFilter(specification);
        return this.createStartRowFilters(specification)
                .reduce(Function::andThen)
                .map(filteringFunction -> filteringFunction.andThen(finalRowFilter))
                .orElse(finalRowFilter);
    }

    private Stream<Function<List<XWPFTableRow>, List<XWPFTableRow>>> createStartRowFilters(final FuelInfoSpecification specification) {
        return this.createStartRowFilters()
                .map(startRowFilter -> createFunctionRowFilter(startRowFilter, specification));
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFinalRowFilter(final FuelInfoSpecification specification) {
        return createFunctionRowFilter(
                this.createFinalRowFilter(),
                specification
        );
    }

    private static <T> Function<List<XWPFTableRow>, T> createFunctionRowFilter(final BiFunction<List<XWPFTableRow>, FuelInfoSpecification, T> source,
                                                                               final FuelInfoSpecification specification) {
        return rows -> source.apply(rows, specification);
    }

    private Optional<FuelInfoLocation> findFuelInfoLocation(final XWPFTableRow fuelInfoHeaderRow,
                                                            final FuelInfoSpecification specification,
                                                            final XWPFTableRow dataRow) {
        final String fuelInfoHeaderCellValue = this.extractFuelInfoHeaderCellValue(specification);
        return findIndexFirstCellByContent(fuelInfoHeaderRow, fuelInfoHeaderCellValue)
                .stream()
                .map(fuelInfoHeaderCellIndex -> this.findCellIndexGenerationNorm(fuelInfoHeaderCellIndex, fuelInfoHeaderCellValue))
                .mapToObj(generationNormCellIndex -> createFuelInfoLocation(dataRow, generationNormCellIndex))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int fuelInfoHeaderCellIndex, final String fuelInfoHeaderCellValue) {
        final int fuelInfoOffset = this.fuelInfoOffsetsByHeaders.get(fuelInfoHeaderCellValue);
        return fuelInfoHeaderCellIndex + fuelInfoOffset;
    }

    private static FuelInfoLocation createFuelInfoLocation(final XWPFTableRow dataRow,
                                                           final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelInfoLocation(dataRow, generationNormCellIndex, consumptionCellIndex);
    }
}
