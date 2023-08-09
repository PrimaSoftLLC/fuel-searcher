package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moving;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.AbstractMovingAndMouldingTableFuelInfoSearchingService;

public abstract class AbstractMovingTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_YIELD = 3;
    private static final int CELL_INDEX_WITH_WORKING_WIDTH = 4;

    public AbstractMovingTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                       final FuelDocument fuelDocument,
                                                       final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }

    @Override
    protected final int findIndexCellOfWorkingWidth() {
        return CELL_INDEX_WITH_WORKING_WIDTH;
    }

    @Override
    protected final int findIndexCellOfYield() {
        return CELL_INDEX_WITH_YIELD;
    }

}
