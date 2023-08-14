package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class FirstTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingBySpecificResistanceService {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";
    private static final int FIRST_FUEL_INFO_OFFSET = 2;

    public FirstTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }
}
