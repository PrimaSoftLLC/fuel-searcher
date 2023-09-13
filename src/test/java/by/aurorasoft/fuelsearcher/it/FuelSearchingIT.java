package by.aurorasoft.fuelsearcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.TableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable.*;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound.*;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.success.*;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.*;

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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("successFuelSearchingArgumentsProvider")
    public void fuelShouldBeFound(final FuelSpecification specification, final Fuel expected)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, OK);
        final boolean testSuccess = this.isFuelSearchingSuccess(actualResponse, expected);
        assertTrue(testSuccess);
    }

    @ParameterizedTest
    @MethodSource("notFoundFuelSearchingArgumentsProvider")
    public void fuelShouldNotBeFound(final FuelSpecification specification)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, NOT_FOUND);
        final boolean testSuccess = isNoSuchFuelError(actualResponse);
        assertTrue(testSuccess);
    }

    @ParameterizedTest
    @MethodSource("notAcceptableFuelSearchingArgumentsProvider")
    public void fuelShouldNotBeFoundBecauseOfNotValidSpecification(final FuelSpecification specification,
                                                                   final Set<String> expectedFailedPropertyNames)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, NOT_ACCEPTABLE);
        assertTrue(isNotValidSpecificationError(actualResponse));

        final Set<String> actualFailedPropertyNames = findFailedPropertyNames(actualResponse);
        assertEquals(expectedFailedPropertyNames, actualFailedPropertyNames);
    }

    private static Stream<Arguments> successFuelSearchingArgumentsProvider() {
        return SUCCESS_ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }

    private static Stream<Arguments> notFoundFuelSearchingArgumentsProvider() {
        return NOT_FOUND_ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }

    private static Stream<Arguments> notAcceptableFuelSearchingArgumentsProvider() {
        return NOT_ACCEPTABLE_ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }

    private boolean isFuelSearchingSuccess(final String actualResponse, final Fuel expectedFuel) {
        final String expectedResponse = this.mapToJson(expectedFuel);
        return Objects.equals(expectedResponse, actualResponse);
    }

    private String mapToJson(final Fuel fuel) {
        try {
            return this.objectMapper.writeValueAsString(fuel);
        } catch (final JsonProcessingException cause) {
            throw new RuntimeException(cause);
        }
    }
}
