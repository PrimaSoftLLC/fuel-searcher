package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class NineteenthTableFuelInfoSearchingService extends AbstractMouldingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА";

    public NineteenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                   final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
