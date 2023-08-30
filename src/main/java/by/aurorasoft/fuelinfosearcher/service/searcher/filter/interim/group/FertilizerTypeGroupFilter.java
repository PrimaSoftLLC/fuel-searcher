package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractFertilizerType;

public final class FertilizerTypeGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";

    public FertilizerTypeGroupFilter(final int filtrationCellIndex) {
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
