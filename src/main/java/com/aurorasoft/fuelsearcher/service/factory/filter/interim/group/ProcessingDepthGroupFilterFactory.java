package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.ProcessingDepthGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ProcessingDepthExtractor;
import org.springframework.stereotype.Component;

@Component
public final class ProcessingDepthGroupFilterFactory
        extends GroupFilterFactory<ProcessingDepthGroupFilter, ProcessingDepthExtractor> {

    public ProcessingDepthGroupFilterFactory(final ProcessingDepthExtractor processingDepthExtractor) {
        super(processingDepthExtractor);
    }

    @Override
    protected ProcessingDepthGroupFilter create(final ProcessingDepthExtractor processingDepthExtractor,
                                                final int filtrationCellIndex) {
        return new ProcessingDepthGroupFilter(processingDepthExtractor, filtrationCellIndex);
    }
}
