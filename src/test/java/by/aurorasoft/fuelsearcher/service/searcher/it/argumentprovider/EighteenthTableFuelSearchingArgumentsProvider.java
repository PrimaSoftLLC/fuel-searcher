package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class EighteenthTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 920.2")
                                .machinery("Krone Swadro 807")
                                .workingWidth("6.2")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(18.4, 2.46)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1+")
                                .machinery("MILLENNIUM V18-7GW")
                                .workingWidth("10.5")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(39.6, 1.28)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelFactory.apply(29.1, 1.78)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("not existing")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("not existing")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}