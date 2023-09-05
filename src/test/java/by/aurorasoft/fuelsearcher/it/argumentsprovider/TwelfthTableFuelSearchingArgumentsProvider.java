package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class TwelfthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Berthoud Raptor 4200, 36 м")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("До 100")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(66.3, 0.93))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mecosan Technoma Lazer 4240, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1001...2000 м")
                                .spreadRate("550...600")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(42, 1.53))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Amazone Pantera 4502, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом до 500 м")
                                .spreadRate("400...450")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(44.2, 1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(36.8, 0.79))
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("not existing")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing charging method and transport distance
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("not existing")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing spread rate
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("not existing")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("not existing")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .build(),
                //not existing fertilizer type
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
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