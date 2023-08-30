package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSpecificResistance;

public final class SpecificResistanceRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    public SpecificResistanceRowFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractSpecificResistance(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
