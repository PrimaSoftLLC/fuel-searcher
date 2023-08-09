package by.aurorasoft.fuelinfosearcher.service.searching.simple.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class FourthTableFuelInfoSearchingService extends AbstractSoilTreatmentFuelInfoSearchingService {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                               final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
