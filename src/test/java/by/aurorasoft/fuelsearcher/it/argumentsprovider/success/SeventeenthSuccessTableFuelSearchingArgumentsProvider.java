package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SeventeenthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";

    public SeventeenthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("ГРЛ-9.6")
                                .workingWidth("9.6")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(27.8, 2.3))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Spider 600/6 ALP")
                                .workingWidth("6")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(32.2, 1.9))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(45.2, 1.4))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build())
                        .build()
        );
    }
}
