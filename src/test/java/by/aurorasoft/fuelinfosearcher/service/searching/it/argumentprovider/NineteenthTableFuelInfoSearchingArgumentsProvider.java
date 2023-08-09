package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public final class NineteenthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSeacrhingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                //TODO: add arguments
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ")
                                .tractor("Fendt 515c")
                                .machinery("KRONE Comprima CV 150 XC")
                                .workingWidth("5,5")
                                .yield("До 1,0")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(12.8, 4.35)
                )
        );
    }

}
