package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

//Удалить \n в заголовках таблицы
public final class ThirteenTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("0.25...0.75")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(259.8, 0.4)
                )
        );
    }

}
