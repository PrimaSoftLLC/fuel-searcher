package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

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
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(259.8, 0.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("6.1...7")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(143.5, 1.17)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("0.76...1.25")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(255.1, 0.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("18.1...21")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(65.3, 2.83)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("0.76...1.25")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(225.7, 0.39)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("18.1...21")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(47.6, 4.6)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("0.25...0.75")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(253.9, 0.39)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("21.1...24")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(56.7, 3.53)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("6.1...7")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(125.9, 1.36)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("5.1...6")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(139.3, 0.93)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("14.1...16")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(65.7, 2.76)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 8430")
                                .machinery("МТУ-24")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("28.1...32")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(30.9, 6.94)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("1.76...2.25")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(198., 0.49)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("1.76...2.25")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(188.6, 0.55)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(50.5, 3.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("3.26...4")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(139.8, 0.76)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("8.1...9")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(82.9, 1.72)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("БЕЛАРУС 3022")
                                .machinery("МТУ-20")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("18.1...21")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(38.9, 4.58)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("2.76...3.25")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(139.1, 0.59)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("10.1...12")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(57.7, 2.23)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы I класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("21.1...24")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(26.5, 5.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Первая группа дорог")
                                .transportDistance("8.1...9")
                                .routingLength("Менее 30")
                                .build(),
                        optionalFuelInfoFactory.apply(66.7, 1.62)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Вторая группа дорог")
                                .transportDistance("8.1...9")
                                .routingLength("31...50")
                                .build(),
                        optionalFuelInfoFactory.apply(59., 1.95)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        optionalFuelInfoFactory.apply(28.6, 4.97)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("not existing")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        empty()
                ),
                //not existing cargo class
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("not existing")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        empty()
                ),
                //not existing road group
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("not existing")
                                .transportDistance("16.1...18")
                                .routingLength("Более 50")
                                .build(),
                        empty()
                ),
                //not existing transport distance
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("not existing")
                                .routingLength("Более 50")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
