package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moving;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class FifteenthTableFuelInfoSearchingService extends AbstractMovingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ";
    private static final String[] ROUTING_LENGTHS = new String[]{
            "Менее 150", "151…200", "201…300", "301…400", "401…600", "601…1000", "Более 1000"
    };
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    public FifteenthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
    }

}
