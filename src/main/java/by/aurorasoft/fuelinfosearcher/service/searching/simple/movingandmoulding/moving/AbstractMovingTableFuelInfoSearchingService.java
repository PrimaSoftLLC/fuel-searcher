package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moving;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.AbstractMovingAndMouldingTableFuelInfoSearchingService;

public abstract class AbstractMovingTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final int CELL_INDEX_YIELD = 3;
    private static final int CELL_INDEX_WORKING_WIDTH = 4;

    public AbstractMovingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                       final String fuelTableName) {
        super(fuelDocument, fuelTableName);
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
