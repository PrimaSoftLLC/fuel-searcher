package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moving;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class SixteenthTableFuelInfoSearchingService extends AbstractMovingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ";
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    public SixteenthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

}
