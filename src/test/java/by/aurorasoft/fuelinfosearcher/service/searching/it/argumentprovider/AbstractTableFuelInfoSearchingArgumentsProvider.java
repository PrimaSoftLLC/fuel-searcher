package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public abstract class AbstractTableFuelInfoSearchingArgumentsProvider {

    public final Stream<Arguments> provide() {
        final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory
                = AbstractTableFuelInfoSearchingArgumentsProvider::createFuelInfoWrappedByOptional;
        return this.provide(optionalFuelInfoFactory);
    }

    protected abstract Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory);

    private static Optional<FuelInfo> createFuelInfoWrappedByOptional(final double generationNorm,
                                                                      final double consumption) {
        final FuelInfo fuelInfo = new FuelInfo(generationNorm, consumption);
        return Optional.of(fuelInfo);
    }
}
