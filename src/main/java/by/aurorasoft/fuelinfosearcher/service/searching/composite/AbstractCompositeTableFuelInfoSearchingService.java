package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelInfoSearchingService;

/**
 * For tables with several element-tables
 */
public abstract class AbstractCompositeTableFuelInfoSearchingService extends AbstractTableFuelInfoSearchingService {

    public AbstractCompositeTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                          final FuelDocument fuelDocument,
                                                          final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }


}
