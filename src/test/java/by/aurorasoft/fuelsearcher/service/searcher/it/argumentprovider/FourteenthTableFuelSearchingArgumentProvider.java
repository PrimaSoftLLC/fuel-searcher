package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FourteenthTableFuelSearchingArgumentProvider extends AbstractTableFuelSearchingArgumentsProvider {


    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("FENDT 936")
                                .machinery("МЖТ-20")
                                .transportDistance("0.25...0.75")
                                .spreadRate("Менее 30")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        optionalFuelFactory.apply(234.9, 0.34)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("CASE IN MAGNUM 340")
                                .machinery("МЖТ-20")
                                .transportDistance("28.1...32")
                                .spreadRate("Более 50")
                                .roadGroup("Вторая группа дорог")
                                .build(),
                        optionalFuelFactory.apply(37.9, 4.49)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        optionalFuelFactory.apply(36.8, 3.05)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("not existing")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing transport distance
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("not existing")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing spread rate
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("not existing")
                                .roadGroup("Первая группа дорог")
                                .build(),
                        empty()
                ),
                //not existing road group
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
