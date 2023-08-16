package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyFirstTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("до 5")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(46.1, 1.87)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("свыше 15,5")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelInfoFactory.apply(155.6, 0.74)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14,5-15,5")
                                .routingLength("401...600")
                                .build(),
                        optionalFuelInfoFactory.apply(140.9, 0.78)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14,5-15,5")
                                .routingLength("401...600")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5")
                                .yield("14,5-15,5")
                                .routingLength("401...600")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("not existing")
                                .yield("14,5-15,5")
                                .routingLength("401...600")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("not existing")
                                .routingLength("401...600")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14,5-15,5")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
