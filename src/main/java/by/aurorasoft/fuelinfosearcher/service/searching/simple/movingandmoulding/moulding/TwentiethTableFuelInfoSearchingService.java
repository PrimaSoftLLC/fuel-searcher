package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding.moulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class TwentiethTableFuelInfoSearchingService extends AbstractMouldingTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА";
    private static final int FIRST_FUEL_INFO_OFFSET = 1;

    public TwentiethTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

}
