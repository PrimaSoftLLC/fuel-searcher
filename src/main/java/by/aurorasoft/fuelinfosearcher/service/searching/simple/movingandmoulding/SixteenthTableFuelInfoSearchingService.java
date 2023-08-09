package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class SixteenthTableFuelInfoSearchingService extends AbstractMovingAndMouldingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ";

    public SixteenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                  final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
