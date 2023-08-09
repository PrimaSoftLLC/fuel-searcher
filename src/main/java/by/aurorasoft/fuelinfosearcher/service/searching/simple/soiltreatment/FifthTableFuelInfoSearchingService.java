package by.aurorasoft.fuelinfosearcher.service.searching.simple.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class FifthTableFuelInfoSearchingService extends AbstractSoilTreatmentFuelInfoSearchingService {
    private static final String TABLE_NAME = "ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ";

    public FifthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                              final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }
}
