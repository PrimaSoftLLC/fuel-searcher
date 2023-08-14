package by.aurorasoft.fuelinfosearcher.service.searching.simple.sowing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class TenthTableFuelInfoSearchingService extends AbstractSowingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    public TenthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

}
