package by.aurorasoft.fuelinfosearcher.service.searching.simple.rackingandturninghay;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    protected final Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                AbstractRackingAndTurningRayFuelInfoSearchingService::findRowsWithoutYieldRow,
                AbstractRackingAndTurningRayFuelInfoSearchingService::findRowsByTractor,
                AbstractRackingAndTurningRayFuelInfoSearchingService::findRowsByMachinery
        );
    }

    @Override
    protected final FinalRowFilter createFinalRowFilter() {
        return AbstractRackingAndTurningRayFuelInfoSearchingService::findRowByWorkingWidth;
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    @SuppressWarnings("unused")
    private static List<XWPFTableRow> findRowsWithoutYieldRow(final List<XWPFTableRow> rows,
                                                              final FuelSpecification specification) {
        return range(0, rows.size())
                .filter(i -> i != ROW_INDEX_YIELD)
                .mapToObj(rows::get)
                .toList();
    }

    private static List<XWPFTableRow> findRowsByTractor(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByTractor(
                rows,
                specification,
                CELL_INDEX_TRACTOR
        );
    }

    private static List<XWPFTableRow> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                          final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByMachinery(
                rows,
                specification,
                CELL_INDEX_MACHINERY
        );
    }

    private static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                                final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByWorkingWidth(
                rows,
                specification,
                CELL_INDEX_WORKING_WIDTH
        );
    }
}
