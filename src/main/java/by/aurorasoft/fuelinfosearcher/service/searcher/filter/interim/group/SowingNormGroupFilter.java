package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSowingNorm;

public final class SowingNormGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Норма высева (семян )?\\d+(-\\d+)? кг/га";

    public SowingNormGroupFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractSowingNorm(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
