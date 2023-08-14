package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class SecondTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingBySpecificResistanceService {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    public SecondTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }
}
