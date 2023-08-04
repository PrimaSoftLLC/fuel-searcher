package by.aurorasoft.fuelinfosearcher.service.searching.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class PloughingStubbleFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingBySpecificResistanceServices {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public PloughingStubbleFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                    final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }
}
