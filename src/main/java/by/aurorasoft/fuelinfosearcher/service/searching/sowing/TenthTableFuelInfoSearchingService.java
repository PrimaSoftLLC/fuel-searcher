package by.aurorasoft.fuelinfosearcher.service.searching.sowing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class TenthTableFuelInfoSearchingService extends AbstractSowingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";

    public TenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                              final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }

}
