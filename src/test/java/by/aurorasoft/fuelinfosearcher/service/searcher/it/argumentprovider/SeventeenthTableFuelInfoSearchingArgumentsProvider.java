package by.aurorasoft.fuelinfosearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SeventeenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 1221")
                                .machinery("ГРЛ-9.6")
                                .workingWidth("9.6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(27.8, 2.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 1221")
                                .machinery("Spider 600/6 ALP")
                                .workingWidth("6")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelInfoFactory.apply(32.2, 1.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelInfoFactory.apply(45.2, 1.4)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("not existing")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
