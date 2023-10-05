package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.WorkingWidthFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.WorkingWidthExtractor;
import org.springframework.stereotype.Component;

@Component
public final class WorkingWidthFinalFilterFactory extends FinalFilterFactory<WorkingWidthFinalFilter, WorkingWidthExtractor> {

    public WorkingWidthFinalFilterFactory(final WorkingWidthExtractor workingWidthExtractor) {
        super(workingWidthExtractor);
    }

    @Override
    protected WorkingWidthFinalFilter create(final WorkingWidthExtractor workingWidthExtractor,
                                             final int filtrationCellIndex) {
        return new WorkingWidthFinalFilter(workingWidthExtractor, filtrationCellIndex);
    }
}
