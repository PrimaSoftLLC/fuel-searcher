package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.AbstractMovingAndMouldingTableFuelInfoSearchingService;

public abstract class AbstractMouldingTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final int CELL_INDEX_YIELD = 4;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    public AbstractMouldingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                         final String fuelTableName,
                                                         final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, firstFuelInfoOffset);
    }

    @Override
    protected final int findCellIndexWorkingWidth() {
        return CELL_INDEX_WORKING_WIDTH;
    }

    @Override
    protected final int findCellIndexYield() {
        return CELL_INDEX_YIELD;
    }

}
