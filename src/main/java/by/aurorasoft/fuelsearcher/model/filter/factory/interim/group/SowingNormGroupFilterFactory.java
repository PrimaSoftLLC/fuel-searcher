package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.SowingNormGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SowingNormExtractor;
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