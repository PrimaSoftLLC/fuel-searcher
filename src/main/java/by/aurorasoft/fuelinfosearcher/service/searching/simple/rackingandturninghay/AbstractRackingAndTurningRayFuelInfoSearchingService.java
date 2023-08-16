package by.aurorasoft.fuelinfosearcher.service.searching.simple.rackingandturninghay;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;
import static java.util.stream.IntStream.range;

public abstract class AbstractRackingAndTurningRayFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = {
            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
    };

    private static final int ROW_INDEX_YIELD = 4;

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    public AbstractRackingAndTurningRayFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                                final String fuelTableName) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                        final FuelInfoSpecification specification) {
        final List<XWPFTableRow> rowsWithoutYieldRow = copyWithoutYieldRow(elementTableRows);
        return findRowsByTractor(rowsWithoutYieldRow, specification, CELL_INDEX_TRACTOR)
                .flatMap(rows -> findRowsByMachinery(rows, specification, CELL_INDEX_MACHINERY))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification, CELL_INDEX_WORKING_WIDTH));
    }

    @Override
    protected String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

    private static List<XWPFTableRow> copyWithoutYieldRow(final List<XWPFTableRow> elementTableRows) {
        return range(0, elementTableRows.size())
                .filter(i -> i != ROW_INDEX_YIELD)
                .mapToObj(elementTableRows::get)
                .toList();
    }
}
