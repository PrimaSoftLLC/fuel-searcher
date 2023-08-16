package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class NineteenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("До 1")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(12.8, 4.35)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("4,6-5")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(50.7, 1.17)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("4,1-4,5")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(48.6, 1.21)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("4,1-4,5")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("not existing")
                                .workingWidth("5,5")
                                .yield("4,1-4,5")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("not existing")
                                .yield("4,1-4,5")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("not existing")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("4,1-4,5")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
