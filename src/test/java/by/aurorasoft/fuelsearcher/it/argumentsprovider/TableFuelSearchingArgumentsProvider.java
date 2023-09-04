package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public abstract class TableFuelSearchingArgumentsProvider {

    public final Stream<Arguments> provide() {
        return this.createFuelSearchingArguments().map(TableFuelSearchingArgumentsProvider::mapToArguments);
    }

    protected abstract Stream<FuelSearchingArguments> createFuelSearchingArguments();

    private static Arguments mapToArguments(final FuelSearchingArguments fuelSearchingArguments) {
        return Arguments.of(
                fuelSearchingArguments.getSpecification(),
                fuelSearchingArguments.findExpected()
        );
    }
}
