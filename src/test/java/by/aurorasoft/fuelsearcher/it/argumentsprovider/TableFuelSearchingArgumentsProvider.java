package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public abstract class TableFuelSearchingArgumentsProvider {

    public final Stream<Arguments> provide() {
        return this.createFuelArguments().map(TableFuelSearchingArgumentsProvider::mapToArguments);
    }

    protected abstract Stream<FuelArguments> createFuelArguments();

    private static Arguments mapToArguments(final FuelArguments fuelArguments) {
        return Arguments.of(
                fuelArguments.getSpecification(),
                fuelArguments.getExpected()
        );
    }
}
