package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentySecondTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("до 9")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(2.4, 20.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15–17")
                                .routingLength("151…200")
                                .build(),
                        optionalFuelInfoFactory.apply(2.2, 25.1)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15–17")
                                .routingLength("201…300")
                                .build(),
                        optionalFuelInfoFactory.apply(2.3, 24.7)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("not existing tractor")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15–17")
                                .routingLength("201…300")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("1")
                                .yield("15–17")
                                .routingLength("201…300")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("not existing")
                                .yield("15–17")
                                .routingLength("201…300")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("not existing")
                                .routingLength("201…300")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА")
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15–17")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
