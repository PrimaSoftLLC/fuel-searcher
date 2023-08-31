package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.group.ProcessingDepthGroupFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.ProcessingDepthExtractor;
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
