package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class EighthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSeacrhingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 3522")
                                .machinery("Horsch Maestro 24 S")
                                .workingWidth("16,8")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 15 кг/га")
                                .build(),
                        optionalFuelInfoFactory.apply(32.9, 8.2)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("Ферабокс Футура Макси 8")
                                .workingWidth("5,6")
                                .routingLength("150–200")
                                .sowingNorm("Норма высева 30 кг/га")
                                .build(),
                        optionalFuelInfoFactory.apply(13.6, 3.6)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        optionalFuelInfoFactory.apply(18.8, 3.1)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("not existing")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("-5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("1000-1500")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 1000000000 кг/га")
                                .build(),
                        empty()
                )
        );
    }

}
