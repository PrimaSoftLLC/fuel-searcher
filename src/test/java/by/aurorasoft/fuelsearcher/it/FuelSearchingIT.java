package by.aurorasoft.fuelsearcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.*;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil.doRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@AutoConfigureMockMvc
public final class FuelSearchingIT extends AbstractContextTest {
    private static final String EXPECTED_REGEX_MESSAGE_ERROR_NO_SUCH_FUEL = "\\{\"httpStatus\":\"NOT_FOUND\","
            + "\"message\":\"Fuel with given properties doesn't exist\","
            + "\"dateTime\":\"\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}\"}";

    private static final List<TableFuelSearchingArgumentsProvider> ARGUMENTS_PROVIDERS = List.of(
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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("fuelSearchingArgumentsProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelShouldBeFound(final FuelSpecification specification, final Optional<Fuel> optionalExpectedFuel)
            throws Exception {
        final HttpStatus expectedHttpStatus = optionalExpectedFuel.map(fuel -> OK).orElse(NOT_FOUND);
        final String actualResponse = doRequest(this.mockMvc, specification, expectedHttpStatus);
        final boolean testSuccess = optionalExpectedFuel
                .map(expectedFuel -> this.isFuelSearchingSuccess(actualResponse, expectedFuel))
                .orElseGet(() -> isNoSuchFuelError(actualResponse));
        assertTrue(testSuccess);
    }

    private static Stream<Arguments> fuelSearchingArgumentsProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }

    private boolean isFuelSearchingSuccess(final String actualResponse, final Fuel expectedFuel) {
        final String expectedResponse = this.mapToJson(expectedFuel);
        return Objects.equals(expectedResponse, actualResponse);
    }

    private static boolean isNoSuchFuelError(final String actualResponse) {
        return actualResponse.matches(EXPECTED_REGEX_MESSAGE_ERROR_NO_SUCH_FUEL);
    }

    private String mapToJson(final Fuel fuel) {
        try {
            return this.objectMapper.writeValueAsString(fuel);
        } catch (final JsonProcessingException cause) {
            throw new RuntimeException(cause);
        }
    }
}
