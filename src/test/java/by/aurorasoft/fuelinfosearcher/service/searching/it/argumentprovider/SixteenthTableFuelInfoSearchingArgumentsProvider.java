package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SixteenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Fendt-933")
                                .machinery("Krone-B 1000 CV Collet")
                                .workingWidth("9.7")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(31.5, 4.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 3022")
                                .machinery("КМР-9П")
                                .workingWidth("9")
                                .yield("свыше 35")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(27.9, 7.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(7.5, 5.6)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("not existing")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 920.2")
                                .machinery("not existing")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("not exsting")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("not existing")
                                .routingLength("Менее 150")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
