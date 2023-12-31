package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.FertilizerTypeGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.FertilizerTypeExtractor;
import org.springframework.stereotype.Component;

@Component
public final class FertilizerTypeGroupFilterFactory
        extends GroupFilterFactory<FertilizerTypeGroupFilter, FertilizerTypeExtractor> {

    public FertilizerTypeGroupFilterFactory(final FertilizerTypeExtractor fertilizerTypeExtractor) {
        super(fertilizerTypeExtractor);
    }

    @Override
    protected FertilizerTypeGroupFilter create(final FertilizerTypeExtractor fertilizerTypeExtractor,
                                               final int filtrationCellIndex) {
        return new FertilizerTypeGroupFilter(fertilizerTypeExtractor, filtrationCellIndex);
    }
}
