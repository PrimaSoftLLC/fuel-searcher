package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySecondTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ЛЕНТ ЛЬНА";

    public TwentySecondTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("до 9")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(2.4, 20.5))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(2.2, 25.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .expected(new Fuel(2.3, 24.7))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing tractor")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("not existing")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("not existing")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
