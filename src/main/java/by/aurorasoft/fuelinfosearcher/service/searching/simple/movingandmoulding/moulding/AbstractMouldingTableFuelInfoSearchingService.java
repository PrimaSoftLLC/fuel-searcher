package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.AbstractMovingAndMouldingTableFuelInfoSearchingService;

public abstract class AbstractMouldingTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_YIELD = 4;
    private static final int CELL_INDEX_WITH_WORKING_WIDTH = 3;

    public AbstractMouldingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                         final String fuelTableName,
                                                         final String[] routingLengths,
                                                         final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
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
