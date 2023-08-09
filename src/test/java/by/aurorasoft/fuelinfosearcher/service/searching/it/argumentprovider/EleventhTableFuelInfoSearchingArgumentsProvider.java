package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class EleventhTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSeacrhingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("CASE IN Puma 210")
                                .machinery("AMAZONE ZG B 8200 SUPER")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2,0")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(71.2, 1.42)
                ),
                //TODO: doesn't work
//                Arguments.of(
//                        FuelInfoSpecification.builder()
//                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
//                                .tractor("CASE IN Puma 210")
//                                .machinery("Amazone ZA M 3000")
//                                .chargingMethodAndTransportDistance("Механизированный с подъездом 0,5…1,5")
//                                .spreadRate("6,1…8,0")
//                                .routingLength("Менее 150")
//                                .fertilizerType("Кристаллические удобрения")
//                                .build(),
//                        optionalFuelInfoFactory.apply(40.3, 1.95)
//                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("УРМ 10")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("8,1…10,0")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(76.6, 1.19)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("REWO 8200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Пылевидные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(37.0, 2.11)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("SULKY XT 160")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("2,1…4,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(73.8, 1.4)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1221")
                                .machinery("RCW 10000")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11,1…13,0")
                                .spreadRate("8,1…10,0")
                                .routingLength("Более 1000")
                                .fertilizerType("Пылевидные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(29.3, 2.24)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1221")
                                .machinery("Amazone ZA-M-1200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("8,1…10,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(27.4, 1.99)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("New Holland TD 5.110")
                                .machinery("Amazone ZA-M-1001 с надставкой бункера 500 л")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5,1…7,0")
                                .spreadRate("до 2,0")
                                .routingLength("201–300")
                                .fertilizerType("Кристаллические удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(40.8, 1.25)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("Amazone ZA-M-900")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11,1…13,0")
                                .spreadRate("8,1…10,0")
                                .routingLength("201–300")
                                .fertilizerType("Кристаллические удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(5.1, 9.05)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("Tornado 1300")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5,1…7,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(13.1, 3.91)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY XT 100 Polivrac")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2,0")
                                .routingLength("201–300")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(75.5, 0.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        optionalFuelInfoFactory.apply(20., 2.85)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("not existing")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing chargingMethodAndTransportDistance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 899999999999…999999999999")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing spread rate
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("10000,1…2000000,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("10000–20000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        empty()
                ),
                //not existing fertilizer type
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Выдуманные удобрения")
                                .build(),
                        empty()
                )
        );
    }

}
