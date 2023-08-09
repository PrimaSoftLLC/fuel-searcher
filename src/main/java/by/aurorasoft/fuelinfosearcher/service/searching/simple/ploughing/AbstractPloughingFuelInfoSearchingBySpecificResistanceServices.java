package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSpecificResistance;

public abstract class AbstractPloughingFuelInfoSearchingBySpecificResistanceServices
        extends AbstractPloughingFuelInfoSearchingServices {

    private static final String REGEX_CONTENT_SPECIFIC_RESISTANCE = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";


    public AbstractPloughingFuelInfoSearchingBySpecificResistanceServices(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                                          final FuelDocument fuelDocument,
                                                                          final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
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
