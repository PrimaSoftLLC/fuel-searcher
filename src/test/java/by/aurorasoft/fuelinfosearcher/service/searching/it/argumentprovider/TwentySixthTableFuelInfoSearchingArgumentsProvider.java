package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentySixthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("JAGUAR 970")
                                .workingWidth("9")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(102.5, 2.95)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("BIG X850")
                                .workingWidth("9")
                                .yield("65-70")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(240.2, 1.24)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(99.8, 1.57)
                ),
                //not existing combine
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("not existing")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("JOHN DEERE 8400")
                                .workingWidth("not existing")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("not existing")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
