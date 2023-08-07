package by.aurorasoft.fuelinfosearcher.service.searching.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class ContinuousAndCombinedSoilTreatmentFuelInfoSearchingService extends AbstractSoilTreatmentFuelInfoSearchingService {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public ContinuousAndCombinedSoilTreatmentFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                                      final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
