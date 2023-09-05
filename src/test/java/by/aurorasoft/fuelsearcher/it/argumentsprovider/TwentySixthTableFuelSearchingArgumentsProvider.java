package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySixthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentySixthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JAGUAR 970")
                                .workingWidth("9")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(102.5, 2.95))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("BIG X850")
                                .workingWidth("9")
                                .yield("65-70")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(240.2, 1.24))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(99.8, 1.57))
                        .build(),
                //not existing combine
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("not existing")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("not existing")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("not existing")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }
}
