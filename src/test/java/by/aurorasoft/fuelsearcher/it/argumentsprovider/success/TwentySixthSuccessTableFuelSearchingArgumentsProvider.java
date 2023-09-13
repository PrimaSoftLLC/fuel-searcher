package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySixthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentySixthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JAGUAR 970")
                                .workingWidth("9")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(102.5, 2.95))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("BIG X850")
                                .workingWidth("9")
                                .yield("65-70")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(240.2, 1.24))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(99.8, 1.57))
                        .build()
        );
    }
}
