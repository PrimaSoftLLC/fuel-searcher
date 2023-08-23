package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSowingNorm;

public final class SowingNormRowFilter extends AbstractGroupRowFilter {
    private static final String GROUP_VALUE_REGEX = "Норма высева (семян )?\\d+(-\\d+)? кг/га";

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractSowingNorm(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}