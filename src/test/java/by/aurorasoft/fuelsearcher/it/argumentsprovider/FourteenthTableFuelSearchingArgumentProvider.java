package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class FourteenthTableFuelSearchingArgumentProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("FENDT 936")
                                .machinery("МЖТ-20")
                                .transportDistance("0.25...0.75")
                                .spreadRate("Менее 30")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .expected(new Fuel(234.9, 0.34))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("CASE IN MAGNUM 340")
                                .machinery("МЖТ-20")
                                .transportDistance("28.1...32")
                                .spreadRate("Более 50")
                                .roadGroup("Вторая группа дорог")
                                .build())
                        .expected(new Fuel(37.9, 4.49))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .expected(new Fuel(36.8, 3.05))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("not existing")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing transport distance
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("not existing")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing spread rate
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("not existing")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing road group
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ")
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("not existing")
                                .build())
                        .build()
        );
    }

}
