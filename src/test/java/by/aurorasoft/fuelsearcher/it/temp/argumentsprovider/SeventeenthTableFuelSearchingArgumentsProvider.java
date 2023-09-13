package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SeventeenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";

    public SeventeenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("ГРЛ-9.6")
                                .workingWidth("9.6")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(27.8, 2.3))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Spider 600/6 ALP")
                                .workingWidth("6")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(32.2, 1.9))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(45.2, 1.4))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
