package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;
import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class EighteenthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СГРЕБАНИЕ СЕНА В ВАЛКИ";

    public EighteenthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("Krone Swadro 807")
                                .workingWidth("6.2")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(18.4, 2.46))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1+")
                                .machinery("MILLENNIUM V18-7GW")
                                .workingWidth("10.5")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(39.6, 1.28))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(29.1, 1.78))
                        .build()
        );
    }
}
