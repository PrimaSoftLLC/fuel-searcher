package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.group.FertilizerTypeGroupFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.FertilizerTypeExtractor;
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
