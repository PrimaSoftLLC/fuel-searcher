package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractProcessingDepth;

public final class ProcessingDepthRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Глубина обработки \\d+...\\d+ см";

    public ProcessingDepthRowFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractProcessingDepth(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
