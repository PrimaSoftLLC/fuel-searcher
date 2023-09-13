package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable.*;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound.*;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.success.*;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public final class FuelSearchingArgumentsProvidingUtil {
    private static final List<SuccessTableFuelSearchingArgumentsProvider> SUCCESS_ARGUMENTS_PROVIDERS = List.of(
            new FirstSuccessTableFuelSearchingArgumentsProvider(),
            new SecondSuccessTableFuelSearchingArgumentsProvider(),
            new ThirdSuccessTableFuelSearchingArgumentsProvider(),
            new FourthSuccessTableFuelSearchingArgumentsProvider(),
            new FifthSuccessTableFuelSearchingArgumentsProvider(),
            new SixthSuccessTableFuelSearchingArgumentsProvider(),
            new SeventhSuccessTableFuelSearchingArgumentsProvider(),
            new EighthSuccessTableFuelSearchingArgumentsProvider(),
            new NinthSuccessTableFuelSearchingArgumentsProvider(),
            new TenthSuccessTableFuelSearchingArgumentsProvider(),
            new EleventhSuccessTableFuelSearchingArgumentsProvider(),
            new TwelfthSuccessTableFuelSearchingArgumentsProvider(),
            new ThirteenthSuccessTableFuelSearchingArgumentsProvider(),
            new FourteenthSuccessTableFuelSearchingArgumentsProvider(),
            new FifteenthSuccessTableFuelSearchingArgumentsProvider(),
            new SixteenthSuccessTableFuelSearchingArgumentsProvider(),
            new SeventeenthSuccessTableFuelSearchingArgumentsProvider(),
            new EighteenthSuccessTableFuelSearchingArgumentsProvider(),
            new NineteenthSuccessTableFuelSearchingArgumentsProvider(),
            new TwentiethSuccessTableFuelSearchingArgumentsProvider(),
            new TwentyFirstSuccessTableFuelSearchingArgumentsProvider(),
            new TwentySecondSuccessTableFuelSearchingArgumentsProvider(),
            new TwentyThirdSuccessTableFuelSearchingArgumentsProvider(),
            new TwentyFourthSuccessTableFuelSearchingArgumentsProvider(),
            new TwentyFifthSuccessTableFuelSearchingArgumentsProvider(),
            new TwentySixthSuccessTableFuelSearchingArgumentsProvider(),
            new TwentySeventhSuccessTableFuelSearchingArgumentsProvider()
    );

    private static final List<NotFoundTableFuelSearchingArgumentsProvider> NOT_FOUND_ARGUMENTS_PROVIDERS = List.of(
            new FirstNotFoundTableFuelSearchingArgumentsProvider(),
            new SecondNotFoundTableFuelSearchingArgumentsProvider(),
            new ThirdNotFoundTableFuelSearchingArgumentsProvider(),
            new FourthNotFoundTableFuelSearchingArgumentsProvider(),
            new FifthNotFoundTableFuelSearchingArgumentsProvider(),
            new SixthNotFoundTableFuelSearchingArgumentsProvider(),
            new SeventhNotFoundTableFuelSearchingArgumentsProvider(),
            new EighthNotFoundTableFuelSearchingArgumentsProvider(),
            new NinthNotFoundTableFuelSearchingArgumentsProvider(),
            new TenthNotFoundTableFuelSearchingArgumentsProvider(),
            new EleventhNotFoundTableFuelSearchingArgumentsProvider(),
            new TwelfthNotFoundTableFuelSearchingArgumentsProvider(),
            new ThirteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new FourteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new FifteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new SixteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new SeventeenthNotFoundTableFuelSearchingArgumentsProvider(),
            new EighteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new NineteenthNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentiethNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentyFirstNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentySecondNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentyThirdNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentyFourthNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentyFifthNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentySixthNotFoundTableFuelSearchingArgumentsProvider(),
            new TwentySeventhNotFoundTableFuelSearchingArgumentsProvider()
    );

    private static final List<NotAcceptableTableFuelSearchingArgumentsProvider> NOT_ACCEPTABLE_ARGUMENTS_PROVIDERS = List.of(
            new FirstNotAcceptableTableFuelSearchingArgumentsProvider(),
            new SecondNotAcceptableTableFuelSearchingArgumentsProvider(),
            new ThirdNotAcceptableTableFuelSearchingArgumentsProvider(),
            new FourthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new FifthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new SixthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new SeventhNotAcceptableTableFuelSearchingArgumentsProvider(),
            new EighthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new NinthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new EleventhNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwelfthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new ThirteenNotAcceptableTableFuelSearchingArgumentsProvider(),
            new FourteenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new FifteenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new SixteenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new SeventeenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new EighteenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new NineteenthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentiethNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentyFirstNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentySecondNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentyThirdNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentyFourthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentyFifthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentySixthNotAcceptableTableFuelSearchingArgumentsProvider(),
            new TwentySeventhNotAcceptableTableFuelSearchingArgumentsProvider()
    );

    public static Stream<Arguments> provideSuccessFuelSearchingArguments() {
        return provide(SUCCESS_ARGUMENTS_PROVIDERS);
    }

    public static Stream<Arguments> provideNotFoundFuelSearchingArguments() {
        return provide(NOT_FOUND_ARGUMENTS_PROVIDERS);
    }

    public static Stream<Arguments> provideNotAcceptableFuelSearchingArguments() {
        return provide(NOT_ACCEPTABLE_ARGUMENTS_PROVIDERS);
    }

    private static Stream<Arguments> provide(final List<? extends TableFuelSearchingArgumentsProvider<?>> providers) {
        return providers.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }
}
