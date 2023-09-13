package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentiethTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА";

    public TwentiethTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("До 1")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(13.9, 6.24))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Krone CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("4.6-5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(45, 1.37))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(29.3, 1.51))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("not existing")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("not exiting")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
