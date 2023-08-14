package by.aurorasoft.fuelinfosearcher.service.searching.simple.sowing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.stereotype.Service;

@Service
public final class SeventhTableFuelInfoSearchingService extends AbstractSowingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: "
            + "ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА";
    private static final int FIRST_FUEL_INFO_OFFSET = 0;


    public SeventhTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

}
