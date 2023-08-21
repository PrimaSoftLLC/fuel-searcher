package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwelfthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Berthoud Raptor 4200, 36 м")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("До 100")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(66.3, 0.93)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mecosan Technoma Lazer 4240, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1001...2000 м")
                                .spreadRate("550...600")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(42.0, 1.53)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Amazone Pantera 4502, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом до 500 м")
                                .spreadRate("400...450")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(44.2, 1.)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(36.8, 0.79)
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("not existing")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing charging method and transport distance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("not existing")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing spread rate
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("not existing")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("not existing")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing fertilizer type
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ")
                                .machinery("Mazzotti IBIS 3180 LP, 24 м")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 2001...3000 м")
                                .spreadRate("550...600")
                                .routingLength("Более 1000")
                                .fertilizerType("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
