package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.CargoClassGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CargoClassExtractor;
import org.springframework.stereotype.Component;

@Component
public final class CargoClassGroupFilterFactory extends GroupFilterFactory<CargoClassGroupFilter, CargoClassExtractor> {

    public CargoClassGroupFilterFactory(final CargoClassExtractor cargoClassExtractor) {
        super(cargoClassExtractor);
    }

    @Override
    protected CargoClassGroupFilter create(final CargoClassExtractor cargoClassExtractor,
                                           final int filtrationCellIndex) {
        return new CargoClassGroupFilter(cargoClassExtractor, filtrationCellIndex);
    }
}
