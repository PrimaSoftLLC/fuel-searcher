package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSoilType;

public final class SoilTypeRowFilter extends AbstractGroupRowFilter {
    private static final String GROUP_VALUE_REGEX = "(Минеральные почвы)|(Торфяные почвы)|(Легкие почвы)|(Средние почвы)|(Тяжелые почвы)";

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractSoilType(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
