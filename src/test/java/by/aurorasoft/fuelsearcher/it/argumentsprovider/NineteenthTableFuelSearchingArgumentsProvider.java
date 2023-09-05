package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NineteenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА";

    public NineteenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("До 1")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(12.8, 4.35))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("4.6-5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(50.7, 1.17))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("4.1-4.5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(48.6, 1.21))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("4.1-4.5")
                                .routingLength("151-200")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("not existing")
                                .workingWidth("5.5")
                                .yield("4.1-4.5")
                                .routingLength("151-200")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("not existing")
                                .yield("4.1-4.5")
                                .routingLength("151-200")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("not existing")
                                .routingLength("151-200")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5.5")
                                .yield("4.1-4.5")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
