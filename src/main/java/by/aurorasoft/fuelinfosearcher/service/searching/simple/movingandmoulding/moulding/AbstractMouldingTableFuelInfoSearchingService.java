package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.AbstractMovingAndMouldingTableFuelInfoSearchingService;

public abstract class AbstractMouldingTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_YIELD = 4;
    private static final int CELL_INDEX_WITH_WORKING_WIDTH = 3;

    public AbstractMouldingTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                         final FuelDocument fuelDocument,
                                                         final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }

    @Override
    protected int findIndexCellOfWorkingWidth() {
        return CELL_INDEX_WITH_WORKING_WIDTH;
    }

    @Override
    protected int findIndexCellOfYield() {
        return CELL_INDEX_WITH_YIELD;
    }

}
