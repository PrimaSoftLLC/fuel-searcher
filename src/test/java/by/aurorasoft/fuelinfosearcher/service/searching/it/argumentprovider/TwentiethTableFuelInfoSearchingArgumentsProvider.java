package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentiethTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 2022")
                                .machinery("CAMPRIMA CF 155 XC")
                                .workingWidth("5,7")
                                .yield("До 1,0")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(13.9, 6.24)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("Krone CAMPRIMA CF 155 XC")
                                .workingWidth("5,7")
                                .yield("4,6–5,0")
                                .routingLength("151…200")
                                .build(),
                        optionalFuelInfoFactory.apply(45., 1.37)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5,5")
                                .yield("3,6–4,0")
                                .routingLength("151…200")
                                .build(),
                        optionalFuelInfoFactory.apply(29.3, 1.51)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5,5")
                                .yield("3,6–4,0")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("5,5")
                                .yield("3,6–4,0")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("not existing")
                                .yield("3,6–4,0")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5,5")
                                .yield("not exiting")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5,5")
                                .yield("3,6–4,0")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
