package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 3522")
                                .machinery("Амкодор «Veras» 12000")
                                .workingWidth("12,0")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 3 кг/га")
                                .build(),
                        optionalFuelInfoFactory.apply(24.5, 9.3)
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
                        optionalFuelInfoFactory.apply(28.1, 7.9)
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
                        optionalFuelInfoFactory.apply(21.3, 5.5)
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
                )
        );
    }

}
