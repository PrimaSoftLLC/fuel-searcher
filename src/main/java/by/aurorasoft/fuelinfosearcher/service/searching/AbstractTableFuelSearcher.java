package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelLocation;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

//TODO: put FuelInfoLocation as inner class
public abstract class AbstractTableFuelSearcher {
    private static final int ROW_INDEX_FUEL_HEADER = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaders;
    private final RowFilterChain filterChain;
    private final Function<FuelSpecification, String> fuelHeaderCellValueExtractor;

    public AbstractTableFuelSearcher(final FuelTable fuelTable,
                                     final String[] fuelHeaders,
                                     final RowFilterChain filterChain,
                                     final Function<FuelSpecification, String> fuelHeaderCellValueExtractor) {
        this.fuelTable = fuelTable;
        this.fuelOffsetsByHeaders = createFuelOffsetsByHeaders(fuelHeaders);
        this.filterChain = filterChain;
        this.fuelHeaderCellValueExtractor = fuelHeaderCellValueExtractor;
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

    //TODO: remove
//    private static FuelTable findTableByName(final FuelDocument fuelDocument, final String fuelTableName) {
//        return fuelDocument.getTables()
//                .stream()
//                .filter(table -> Objects.equals(table.getName(), fuelTableName))
//                .findFirst()
//                .orElseThrow(
//                        () -> new FuelTableNotExistException(
//                                "Table '%s' doesn't exist".formatted(fuelTableName)
//                        )
//                );
//    }

    private static Map<String, Integer> createFuelOffsetsByHeaders(final String[] fuelHeaders) {
        return range(0, fuelHeaders.length)
                .boxed()
                .collect(toMap(i -> fuelHeaders[i], identity()));
    }

    private Optional<Fuel> findFuel(final List<XWPFTableRow> elementTableRows, final FuelSpecification specification) {
        final XWPFTableRow fuelHeaderRow = elementTableRows.get(ROW_INDEX_FUEL_HEADER);
        return this.filterChain.filter(elementTableRows, specification)
                .flatMap(row -> this.findFuelLocation(fuelHeaderRow, specification, row))
                .flatMap(FuelUtil::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow fuelHeaderRow,
                                                    final FuelSpecification specification,
                                                    final XWPFTableRow dataRow) {
        final String fuelHeaderCellValue = this.fuelHeaderCellValueExtractor.apply(specification);
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
