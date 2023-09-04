package by.aurorasoft.fuelsearcher.service.searcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider.*;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearchingManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FuelSearchingIT extends AbstractContextTest {
    private static final List<AbstractTableFuelSearchingArgumentsProvider> ARGUMENTS_PROVIDERS = List.of(
            new EighthTableFuelSearchingArgumentsProvider(),
            new NinthTableFuelSearchingArgumentsProvider(),
            new TenthTableFuelSearchingArgumentsProvider(),
            new EleventhTableFuelSearchingArgumentsProvider(),
            new TwelfthTableFuelSearchingArgumentsProvider(),
            new ThirteenTableFuelSearchingArgumentsProvider(),
            new FourteenthTableFuelSearchingArgumentProvider(),
            new FifteenthTableFuelSearchingArgumentsProvider(),
            new SixteenthTableFuelSearchingArgumentsProvider(),
            new SeventeenthTableFuelSearchingArgumentsProvider(),
            new EighteenthTableFuelSearchingArgumentsProvider(),
            new NineteenthTableFuelSearchingArgumentsProvider(),
            new TwentiethTableFuelSearchingArgumentsProvider(),
            new TwentyFirstTableFuelSearchingArgumentsProvider(),
            new TwentySecondTableFuelSearchingArgumentsProvider(),
            new TwentyThirdTableFuelSearchingArgumentsProvider(),
            new TwentyFourthTableFuelSearchingArgumentsProvider(),
            new TwentyFifthTableFuelSearchingArgumentsProvider(),
            new TwentySixthTableFuelSearchingArgumentsProvider(),
            new TwentySeventhTableFuelSearchingArgumentsProvider()
    );

    @Autowired
    private FuelSearchingManager searchingManager;

    @ParameterizedTest
    @MethodSource("fuelSearchingArgumentProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelInfoShouldBeFound(final FuelSpecification specification, final Optional<Fuel> expected) {
        final Optional<Fuel> actual = this.searchingManager.find(specification);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fuelSearchingArgumentProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(AbstractTableFuelSearchingArgumentsProvider::provide);
    }
}
