package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FourteenthTableFuelInfoSearchingArgumentProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {


    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("FENDT 936")
                                .machinery("МЖТ-20")
                                .transportDistance("0,25...0,75")
                                .spreadRate("Менее 30")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        optionalFuelInfoFactory.apply(234.9, 0.34)
                ),
                //TODO: don't work
//                Arguments.of(
//                        FuelInfoSpecification.builder()
//                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
//                                .tractor("CASE IN MAGNUM 340")
//                                .machinery("МЖТ-20")
//                                .transportDistance("28,1...32")
//                                .spreadRate("Более 50")
//                                .roadGroup("Вторая группа дорог")
//                                .build(),
//                        optionalFuelInfoFactory.apply(37.9, 4.49)
//                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24,1...28")
                                .spreadRate("31...50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        optionalFuelInfoFactory.apply(36.8, 3.05)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("not existing")
                                .machinery("МЖТ-16")
                                .transportDistance("24,1...28")
                                .spreadRate("31...50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .transportDistance("24,1...28")
                                .spreadRate("31...50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing transport distance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("not existing")
                                .spreadRate("31...50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing spread rate
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24,1...28")
                                .spreadRate("not existing")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing road group
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24,1...28")
                                .spreadRate("31...50")
                                .roadGroup("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
