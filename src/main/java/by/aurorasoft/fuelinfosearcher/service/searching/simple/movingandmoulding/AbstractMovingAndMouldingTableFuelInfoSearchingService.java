package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.YieldRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.TractorRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.WorkingWidthRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;

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
    protected final RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(createTractorRowFilter())
                .intermediateFilter(createMachineryRowFilter())
                .intermediateFilter(this.createWorkingWidthRowFilter())
                .conclusiveFilter(this.createYieldRowFilter())
                .build();
    }

    @Override
    protected final String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    protected abstract int findCellIndexWorkingWidth();

    protected abstract int findCellIndexYield();

    private static TractorRowFilter createTractorRowFilter() {
        return new TractorRowFilter(CELL_INDEX_TRACTOR);
    }

    private static MachineryRowFilter createMachineryRowFilter() {
        return new MachineryRowFilter(CELL_INDEX_MACHINERY);
    }

    private WorkingWidthRowFilter createWorkingWidthRowFilter() {
        final int cellIndexWorkingWidth = this.findCellIndexWorkingWidth();
        return new WorkingWidthRowFilter(cellIndexWorkingWidth);
    }

    private YieldRowFilter createYieldRowFilter() {
        final int cellIndexYield = this.findCellIndexYield();
        return new YieldRowFilter(cellIndexYield);
    }
}
