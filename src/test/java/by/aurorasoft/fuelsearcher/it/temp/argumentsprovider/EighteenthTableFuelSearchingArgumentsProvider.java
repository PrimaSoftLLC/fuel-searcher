package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class EighteenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СГРЕБАНИЕ СЕНА В ВАЛКИ";

    public EighteenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("Krone Swadro 807")
                                .workingWidth("6.2")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(18.4, 2.46))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1+")
                                .machinery("MILLENNIUM V18-7GW")
                                .workingWidth("10.5")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(39.6, 1.28))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(29.1, 1.78))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1")
                                .machinery("not existing")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
