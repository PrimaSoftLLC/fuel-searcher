package by.aurorasoft.fuelinfosearcher.service.searching.it;

import by.aurorasoft.fuelinfosearcher.base.AbstractContextTest;
import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider.*;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.FuelInfoSearchingManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FuelInfoSearchingIT extends AbstractContextTest {
    private static final List<AbstractTableFuelInfoSearchingArgumentsProvider> ARGUMENTS_PROVIDERS = List.of(
//            new FirstTableFuelInfoSearchingArgumentsProvider(),
//            new SecondTableFuelInfoSearchingArgumentsProvider(),
//            new ThirdTableFuelInfoSearchingArgumentsProvider(),
//            new FourthTableFuelInfoSearchingArgumentsProvider(),
            new FifthTableFuelInfoSearchingArgumentsProvider(),
            new SixthTableFuelInfoSearchingArgumentsProvider(),
            new SeventhTableFuelInfoSearchingArgumentsProvider(),
            new EighthTableFuelInfoSearchingArgumentsProvider(),
            new NinthTableFuelInfoSearchingArgumentsProvider(),
            new TenthTableFuelInfoSearchingArgumentsProvider(),
            new EleventhTableFuelInfoSearchingArgumentsProvider(),
            new TwelfthTableFuelInfoSearchingArgumentsProvider(),
            new ThirteenTableFuelInfoSearchingArgumentsProvider(),
            new FourteenthTableFuelInfoSearchingArgumentProvider(),
            new FifteenthTableFuelInfoSearchingArgumentsProvider(),
            new SixteenthTableFuelInfoSearchingArgumentsProvider(),
            new SeventeenthTableFuelInfoSearchingArgumentsProvider(),
            new EighteenthTableFuelInfoSearchingArgumentsProvider(),
            new NineteenthTableFuelInfoSearchingArgumentsProvider(),
            new TwentiethTableFuelInfoSearchingArgumentsProvider(),
            new TwentyFirstTableFuelInfoSearchingArgumentsProvider(),
            new TwentySecondTableFuelInfoSearchingArgumentsProvider(),
            new TwentyThirdTableFuelInfoSearchingArgumentsProvider(),
            new TwentyFourthTableFuelInfoSearchingArgumentsProvider(),
            new TwentyFifthTableFuelInfoSearchingArgumentsProvider(),
            new TwentySixthTableFuelInfoSearchingArgumentsProvider(),
            new TwentySeventhTableFuelInfoSearchingArgumentsProvider()
    );

    private final FuelInfoSearchingManager searchingManager = findBean(FuelInfoSearchingManager.class);

    @ParameterizedTest
    @MethodSource("fuelInfoSearchingArgumentProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelInfoShouldBeFound(final FuelSpecification specification, final Optional<Fuel> expected) {
        final Optional<Fuel> actual = this.searchingManager.find(specification);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fuelInfoSearchingArgumentProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(AbstractTableFuelInfoSearchingArgumentsProvider::provide);
    }
}
