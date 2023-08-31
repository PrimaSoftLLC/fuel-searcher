package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public abstract class AbstractTableFuelInfoSearchingArgumentsProvider {

    public final Stream<Arguments> provide() {
        final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory
                = AbstractTableFuelInfoSearchingArgumentsProvider::createFuelInfoWrappedByOptional;
        return this.provide(optionalFuelInfoFactory);
    }

    protected abstract Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory);

    private static Optional<Fuel> createFuelInfoWrappedByOptional(final double generationNorm,
                                                                  final double consumption) {
        final Fuel fuelInfo = new Fuel(generationNorm, consumption);
        return Optional.of(fuelInfo);
    }
}
