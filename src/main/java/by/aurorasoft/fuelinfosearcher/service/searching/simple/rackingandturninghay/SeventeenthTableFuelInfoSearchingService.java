package by.aurorasoft.fuelinfosearcher.service.searching.simple.rackingandturninghay;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class SeventeenthTableFuelInfoSearchingService extends AbstractRackingAndTurningRayFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    public SeventeenthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

}
