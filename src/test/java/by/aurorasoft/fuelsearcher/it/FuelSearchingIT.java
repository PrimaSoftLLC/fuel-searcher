package by.aurorasoft.fuelsearcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.success.*;
import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.TableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.TwentiethTableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

@AutoConfigureMockMvc
public final class FuelSearchingIT extends AbstractContextTest {
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
            new Twen(),

    );

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("fuelSearchingArgumentsProvider")
    public void fuelShouldBeFound(final FuelSpecification specification, final Fuel expected) {

    }

    private static Stream<Arguments> successFuelSearchingArgumentsProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }
}
