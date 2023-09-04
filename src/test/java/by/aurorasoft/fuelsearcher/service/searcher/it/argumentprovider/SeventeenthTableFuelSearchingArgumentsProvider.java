package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SeventeenthTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 1221")
                                .machinery("ГРЛ-9.6")
                                .workingWidth("9.6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(27.8, 2.3)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 1221")
                                .machinery("Spider 600/6 ALP")
                                .workingWidth("6")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(32.2, 1.9)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВОРОШЕНИЕ СЕНА")
                                .tractor("Беларус 82")
                                .machinery("Tonutti Millennium V16")
                                .workingWidth("9.6")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelFactory.apply(45.2, 1.4)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
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
                        FuelSpecification.builder()
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
                        FuelSpecification.builder()
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
                        FuelSpecification.builder()
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
