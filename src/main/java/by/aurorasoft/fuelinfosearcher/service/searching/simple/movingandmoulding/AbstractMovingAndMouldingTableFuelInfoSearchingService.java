package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.conclusive.TEMPConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.start.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

public abstract class AbstractMovingAndMouldingTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;

    public AbstractMovingAndMouldingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                                  final String fuelTableName) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
    }

    @Override
    protected final Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                AbstractMovingAndMouldingTableFuelInfoSearchingService::findRowsByTractor,
                AbstractMovingAndMouldingTableFuelInfoSearchingService::findRowsByMachinery,
                this::findRowsByWorkingWidth
        );
    }

    @Override
    protected final TEMPConclusiveRowFilter createFinalRowFilter() {
        return this::findRowByYield;
    }

    @Override
    protected final String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    protected abstract int findCellIndexWorkingWidth();

    protected abstract int findCellIndexYield();

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

    private List<XWPFTableRow> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                      final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByWorkingWidth(
                rows,
                specification,
                this.findCellIndexWorkingWidth()
        );
    }

    private Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                  final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByYield(
                rows,
                specification,
                this.findCellIndexYield()
        );
    }
}
