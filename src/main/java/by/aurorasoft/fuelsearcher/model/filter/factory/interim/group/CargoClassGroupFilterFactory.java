package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.CargoClassGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.CargoClassExtractor;
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
