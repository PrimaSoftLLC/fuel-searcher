package by.aurorasoft.fuelinfosearcher.service.searching.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.springframework.stereotype.Service;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSoilType;

@Service
public final class ThirdTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingServices {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    private static final String REGEX_SOIL_TYPE_CONTENT = "(Минеральные почвы)|(Торфяные почвы)";

    public ThirdTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                              final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
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
