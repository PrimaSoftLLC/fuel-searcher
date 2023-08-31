package by.aurorasoft.fuelsearcher.service.searcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider.*;
import by.aurorasoft.fuelsearcher.service.searcher.manager.FuelSearchingManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FuelSearchingIT extends AbstractContextTest {
    private static final List<AbstractTableFuelSearchingArgumentsProvider> ARGUMENTS_PROVIDERS = List.of(
            new FirstTableFuelSearchingArgumentsProvider(),
            new SecondTableFuelSearchingArgumentsProvider(),
            new ThirdTableFuelSearchingArgumentsProvider(),
            new FourthTableFuelSearchingArgumentsProvider(),
            new FifthTableFuelSearchingArgumentsProvider(),
            new SixthTableFuelSearchingArgumentsProvider(),
            new SeventhTableFuelSearchingArgumentsProvider(),
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

    private final FuelSearchingManager searchingManager = findBean(FuelSearchingManager.class);

    @ParameterizedTest
    @MethodSource("fuelSearchingArgumentProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelInfoShouldBeFound(final Specification specification, final Optional<Fuel> expected) {
        final Optional<Fuel> actual = this.searchingManager.find(specification);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fuelSearchingArgumentProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(AbstractTableFuelSearchingArgumentsProvider::provide);
    }
}