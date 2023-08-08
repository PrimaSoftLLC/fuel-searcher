package by.aurorasoft.fuelinfosearcher.service.searching.it;

import by.aurorasoft.fuelinfosearcher.base.AbstractContextTest;
import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.FuelInfoSearchingManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;


public final class FuelInfoSearchingIT extends AbstractContextTest {

    private final FuelInfoSearchingManager searchingManager = findBean(FuelInfoSearchingManager.class);

    @ParameterizedTest
    @MethodSource("fuelInfoSearchingArgumentProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelInfoShouldBeFound(final FuelInfoSpecification specification, final Optional<FuelInfo> expected) {
        final Optional<FuelInfo> actual = this.searchingManager.find(specification);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fuelInfoSearchingArgumentProvider() {
        return Stream.of(

                //For table #9
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("John Deere 8400")
                                .machinery("Tempo V-18")
                                .workingWidth("8,1")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 15 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(17.9, 3.45)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 1221")
                                .machinery("Meca V-4")
                                .workingWidth("5,4")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 30 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(15.6, 2.72)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(10.3, 2.64)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("not existing")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("-3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("1000-1500")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 99999999999999999 кг/га")
                                .build(),
                        empty()
                ),

                //For table #10
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 3522")
                                .machinery("Амкодор «Veras» 12000")
                                .workingWidth("12,0")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 3 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(24.5, 9.3)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 3522")
                                .machinery("Amazone Avant 6001-2")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(28.1, 7.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(21.3, 5.5)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("not existing")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("not existing machinery")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //nit existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("-6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("1500–2000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 99999999999999 кг/га")
                                .build(),
                        empty()
                ),

                //For table #11
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
                        createFuelInfoWrappedByOptional(71.2, 1.42)
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
//                        createFuelInfoWrappedByOptional(40.3, 1.95)
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
                        createFuelInfoWrappedByOptional(76.6, 1.19)
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
                        createFuelInfoWrappedByOptional(37.0, 2.11)
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
                        createFuelInfoWrappedByOptional(73.8, 1.4)
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
                        createFuelInfoWrappedByOptional(29.3, 2.24)
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
                        createFuelInfoWrappedByOptional(27.4, 1.99)
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
                        createFuelInfoWrappedByOptional(40.8, 1.25)
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
                        createFuelInfoWrappedByOptional(5.1, 9.05)
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
                        createFuelInfoWrappedByOptional(13.1, 3.91)
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
                        createFuelInfoWrappedByOptional(75.5, 0.9)
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
                        createFuelInfoWrappedByOptional(20., 2.85)
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
