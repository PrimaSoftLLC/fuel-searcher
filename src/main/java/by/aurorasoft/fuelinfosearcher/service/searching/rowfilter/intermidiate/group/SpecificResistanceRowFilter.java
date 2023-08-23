package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSpecificResistance;

public final class SpecificResistanceRowFilter extends AbstractGroupRowFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractSpecificResistance(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
