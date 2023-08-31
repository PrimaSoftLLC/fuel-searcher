package by.aurorasoft.fuelinfosearcher.model.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.ProcessingDepthExtractor;

public final class ProcessingDepthGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Глубина обработки \\d+...\\d+ см";

    public ProcessingDepthGroupFilter(final ProcessingDepthExtractor processingDepthExtractor,
                                      final int filtrationCellIndex) {
        super(processingDepthExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
