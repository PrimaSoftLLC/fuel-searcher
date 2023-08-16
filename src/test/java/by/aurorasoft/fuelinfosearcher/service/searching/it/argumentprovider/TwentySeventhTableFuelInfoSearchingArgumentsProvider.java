package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentySeventhTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСС-25")
                                .cargoClass("Грузы I класса")
                                .transportDistance("0,25-0,75")
                                .roadGroup("I")
                                .build(),
                        optionalFuelInfoFactory.apply(341.7, 0.19)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСС-20")
                                .cargoClass("Грузы II класса")
                                .transportDistance("3,26-4")
                                .roadGroup("II")
                                .build(),
                        optionalFuelInfoFactory.apply(146.9, 0.83)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСТБ-17")
                                .cargoClass("Грузы III класса")
                                .transportDistance("0,25-0,75")
                                .roadGroup("III")
                                .build(),
                        optionalFuelInfoFactory.apply(131.9, 0.48)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-3022")
                                .machinery("ПСС-25")
                                .cargoClass("Грузы IV класса")
                                .transportDistance("0,76-1,25")
                                .roadGroup("III")
                                .build(),
                        optionalFuelInfoFactory.apply(91.9, 0.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-3022")
                                .machinery("ПСС-20")
                                .cargoClass("Грузы II класса")
                                .transportDistance("1,26-1,75")
                                .roadGroup("II")
                                .build(),
                        optionalFuelInfoFactory.apply(201., 0.38)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                //TODO: тут отличие от прошлого только в регистре
                                .tractor("Беларус-3022")
                                .machinery("BAASTRUP 18")
                                .cargoClass("Грузы III класса")
                                .transportDistance("5,1-6")
                                .roadGroup("II")
                                .build(),
                        optionalFuelInfoFactory.apply(54.2, 1.66)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("БЕЛАРУС-1221")
                                .machinery("СТС-12")
                                .cargoClass("Грузы II класса")
                                .transportDistance("3,26-4")
                                .roadGroup("I")
                                .build(),
                        optionalFuelInfoFactory.apply(69.3, 0.8)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45,1-50")
                                .roadGroup("III")
                                .build(),
                        optionalFuelInfoFactory.apply(8.7, 5.65)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("not existing")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45,1-50")
                                .roadGroup("III")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("Беларус 920")
                                .machinery("not existing")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45,1-50")
                                .roadGroup("III")
                                .build(),
                        empty()
                ),
                //not existing cargo class
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("not existing")
                                .transportDistance("45,1-50")
                                .roadGroup("III")
                                .build(),
                        empty()
                ),
                //not existing transport distance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("not existing")
                                .roadGroup("III")
                                .build(),
                        empty()
                ),
                //not existing road group
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ")
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45,1-50")
                                .roadGroup("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
