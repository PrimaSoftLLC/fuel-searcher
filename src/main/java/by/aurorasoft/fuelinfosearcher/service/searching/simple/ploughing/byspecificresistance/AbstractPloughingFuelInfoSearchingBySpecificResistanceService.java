package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.AbstractPloughingFuelInfoSearchingService;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSpecificResistance;

public abstract class AbstractPloughingFuelInfoSearchingBySpecificResistanceService
        extends AbstractPloughingFuelInfoSearchingService {
    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    public AbstractPloughingFuelInfoSearchingBySpecificResistanceService(final FuelDocument fuelDocument,
                                                                         final String fuelTableName,
                                                                         final String[] routingLengths,
                                                                         final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final String extractGroupValue(final FuelInfoSpecification specification) {
        return extractSpecificResistance(specification);
    }

    @Override
    protected final String findGroupValueRegex() {
        return REGEX_CONTENT_SPECIFIC_RESISTANCE;
    }
}
