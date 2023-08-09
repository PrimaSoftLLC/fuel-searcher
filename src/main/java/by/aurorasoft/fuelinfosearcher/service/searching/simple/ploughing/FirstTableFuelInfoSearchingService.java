package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class FirstTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingBySpecificResistanceServices {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    public FirstTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                              final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }
}
