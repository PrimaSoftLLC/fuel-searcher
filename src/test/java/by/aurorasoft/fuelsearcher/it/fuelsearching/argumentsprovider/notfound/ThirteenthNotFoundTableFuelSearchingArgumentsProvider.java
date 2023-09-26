package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ThirteenthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public ThirteenthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .spreadRate("Более 50")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .spreadRate("Более 50")
                                .build())
                        .build(),
                //not existing cargo class
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("not existing")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .spreadRate("Более 50")
                                .build())
                        .build(),
                //not existing road group
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("not existing")
                                .transportDistance("16.1...18")
                                .spreadRate("Более 50")
                                .build())
                        .build(),
                //not existing transport distance
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("not existing")
                                .spreadRate("Более 50")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("РОУМ-14")
                                .cargoClass("Грузы II класса")
                                .roadGroup("Третья группа дорог")
                                .transportDistance("16.1...18")
                                .spreadRate("not existing")
                                .build())
                        .build()
        );
    }
}
