package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.ProcessingDepthExtractor;

public final class ProcessingDepthGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Глубина обработки \\d+...\\d+ см";

    public ProcessingDepthGroupFilter(final ProcessingDepthExtractor processingDepthExtractor,
                                      final int filtrationCellIndex) {
        super(processingDepthExtractor, filtrationCellIndex);
    }

    @Override
    public String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
