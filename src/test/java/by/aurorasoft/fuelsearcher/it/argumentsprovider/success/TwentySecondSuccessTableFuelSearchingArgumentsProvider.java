package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySecondSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ЛЕНТ ЛЬНА";

    public TwentySecondSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("до 9")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(2.4, 20.5))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(2.2, 25.1))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .expected(new Fuel(2.3, 24.7))
                        .build()
        );
    }
}
