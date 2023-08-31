package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.unit.WorkingWidthUnitFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.WorkingWidthExtractor;
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
