package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public abstract class AbstractTableFuelSearchingArgumentsProvider {

    public final Stream<Arguments> provide() {
        final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory
                = AbstractTableFuelSearchingArgumentsProvider::createFuelWrappedByOptional;
        return this.provide(optionalFuelFactory);
    }

    protected abstract Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory);

    private static Optional<Fuel> createFuelWrappedByOptional(final double generationNorm,
                                                              final double consumption) {
        final Fuel fuelInfo = new Fuel(generationNorm, consumption);
        return Optional.of(fuelInfo);
    }
}
