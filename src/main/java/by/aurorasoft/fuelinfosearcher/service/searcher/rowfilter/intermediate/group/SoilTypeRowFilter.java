package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSoilType;

public final class SoilTypeRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "(Минеральные почвы)|(Торфяные почвы)|(Легкие почвы)|(Средние почвы)|(Тяжелые почвы)";

    public SoilTypeRowFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractSoilType(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
