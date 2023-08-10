package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.springframework.stereotype.Service;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSoilType;

@Service
public final class ThirdTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";
    private static final String[] ROUTING_LENGTHS = new String[]{
            "Менее 150", "150–200", "201–300", "301–400", "401–600", "601–1000", "Более 1000"
    };
    private static final int FIRST_FUEL_INFO_OFFSET = 2;
    private static final String REGEX_SOIL_TYPE_CONTENT = "(Минеральные почвы)|(Торфяные почвы)";

    public ThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
    }


    @Override
    protected String extractGroupValue(final FuelInfoSpecification specification) {
        return extractSoilType(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return REGEX_SOIL_TYPE_CONTENT;
    }
}
