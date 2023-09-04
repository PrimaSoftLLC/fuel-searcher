package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentySecondTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("до 9")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(2.4, 20.5)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(2.2, 25.1)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(2.3, 24.7)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("not existing tractor")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("not existing")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("not existing")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
