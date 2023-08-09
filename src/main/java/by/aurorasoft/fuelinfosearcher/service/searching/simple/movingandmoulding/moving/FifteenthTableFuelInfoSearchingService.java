package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moving;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class FifteenthTableFuelInfoSearchingService extends AbstractMovingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ";

    public FifteenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                  final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
