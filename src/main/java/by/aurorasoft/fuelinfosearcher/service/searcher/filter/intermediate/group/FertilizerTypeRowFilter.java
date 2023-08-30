package by.aurorasoft.fuelinfosearcher.service.searcher.filter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractFertilizerType;

public final class FertilizerTypeRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";

    public FertilizerTypeRowFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractFertilizerType(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
