package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwelfthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    public TwelfthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Berthoud Raptor 4200, 36 м")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("До 100")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(66.3, 0.93))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mecosan Technoma Lazer 4240, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1001...2000 м")
                                .spreadRate("550...600")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(42, 1.53))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Amazone Pantera 4502, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом до 500 м")
                                .spreadRate("400...450")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(44.2, 1))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(36.8, 0.79))
                        .build()
        );
    }
}
