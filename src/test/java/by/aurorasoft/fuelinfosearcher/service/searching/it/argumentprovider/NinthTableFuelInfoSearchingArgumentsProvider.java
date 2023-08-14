package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class NinthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("John Deere 8400")
                                .machinery("Tempo V-18")
                                .workingWidth("8,1")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 15 кг/га")
                                .build(),
                        optionalFuelInfoFactory.apply(17.9, 3.45)
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
                        optionalFuelInfoFactory.apply(15.6, 2.72)
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
                        optionalFuelInfoFactory.apply(10.3, 2.64)
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
                )
        );
    }

}
