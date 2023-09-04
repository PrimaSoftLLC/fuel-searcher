package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentiethTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 2022")
                                .machinery("CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("До 1")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(13.9, 6.24)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("Krone CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("4.6-5")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(45., 1.37)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(29.3, 1.51)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("not existing")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("not exiting")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
