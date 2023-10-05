package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.WorkingWidthUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.WorkingWidthExtractor;
import org.springframework.stereotype.Component;

@Component
public final class WorkingWidthUnitFilterFactory
        extends UnitFilterFactory<WorkingWidthUnitFilter, WorkingWidthExtractor> {

    public WorkingWidthUnitFilterFactory(final WorkingWidthExtractor workingWidthExtractor) {
        super(workingWidthExtractor);
    }

    @Override
    protected WorkingWidthUnitFilter create(final WorkingWidthExtractor workingWidthExtractor,
                                            final int filtrationCellIndex) {
        return new WorkingWidthUnitFilter(workingWidthExtractor, filtrationCellIndex);
    }
}
