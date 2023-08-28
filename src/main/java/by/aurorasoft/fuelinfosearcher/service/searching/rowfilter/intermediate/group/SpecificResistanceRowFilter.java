package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSpecificResistance;

public final class SpecificResistanceRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    public SpecificResistanceRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractSpecificResistance(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
