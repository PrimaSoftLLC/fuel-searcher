package com.aurorasoft.fuelsearcher.model.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ProcessingDepthExtractor;

public final class ProcessingDepthGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Глубина обработки \\d+\\.{3}\\d+ см";

    public ProcessingDepthGroupFilter(final ProcessingDepthExtractor processingDepthExtractor,
                                      final int filtrationCellIndex) {
        super(processingDepthExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
