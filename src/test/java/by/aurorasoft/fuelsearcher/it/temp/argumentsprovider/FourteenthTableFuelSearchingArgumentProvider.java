package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourteenthTableFuelSearchingArgumentProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public FourteenthTableFuelSearchingArgumentProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 936")
                                .machinery("МЖТ-20")
                                .transportDistance("0.25...0.75")
                                .spreadRate("Менее 30")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .expected(new Fuel(234.9, 0.34))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("CASE IN MAGNUM 340")
                                .machinery("МЖТ-20")
                                .transportDistance("28.1...32")
                                .spreadRate("Более 50")
                                .roadGroup("Вторая группа дорог")
                                .build())
                        .expected(new Fuel(37.9, 4.49))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
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
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing transport distance
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("not existing")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing spread rate
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("not existing")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing road group
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
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
