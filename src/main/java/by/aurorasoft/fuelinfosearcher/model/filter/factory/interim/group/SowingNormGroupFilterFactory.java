package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.group.SowingNormGroupFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SowingNormExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SowingNormGroupFilterFactory extends GroupFilterFactory<SowingNormGroupFilter, SowingNormExtractor> {

    public SowingNormGroupFilterFactory(final SowingNormExtractor sowingNormExtractor) {
        super(sowingNormExtractor);
    }

    @Override
    protected SowingNormGroupFilter create(final SowingNormExtractor sowingNormExtractor,
                                           final int filtrationCellIndex) {
        return new SowingNormGroupFilter(sowingNormExtractor, filtrationCellIndex);
    }
}
