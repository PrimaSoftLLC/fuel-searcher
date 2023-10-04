package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwelfthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    public TwelfthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("not existing")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing charging method and transport distance
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("not existing")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing spread rate
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("not existing")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("not existing")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing fertilizer type
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("not existing")
                                .build())
                        .build()
        );
    }
}
