package by.aurorasoft.fuelinfosearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FifteenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 2522")
                                .machinery("KDT 941")
                                .workingWidth("9.14")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(28.1, 4.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1523")
                                .machinery("Novocat Alfa Motion 351")
                                .workingWidth("3.46")
                                .yield("свыше 35")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelInfoFactory.apply(11.4, 4.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelInfoFactory.apply(11.7, 4.4)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("not existing")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("not existing")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("not existing")
                                .routingLength("201-300")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ")
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
