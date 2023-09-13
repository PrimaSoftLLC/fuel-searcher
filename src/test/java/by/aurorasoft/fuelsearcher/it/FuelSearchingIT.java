package by.aurorasoft.fuelsearcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
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

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.it.argumentsprovider.FuelSearchingArgumentsProvidingUtil.*;
import static by.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.*;

@AutoConfigureMockMvc
public final class FuelSearchingIT extends AbstractContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("successFuelSearchingArgumentsProvider")
    public void fuelShouldBeFound(final FuelSpecification specification, final Fuel expected)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, OK);
        assertTrue(this.isFuelSearchingSuccess(actualResponse, expected));
    }

    @ParameterizedTest
    @MethodSource("notFoundFuelSearchingArgumentsProvider")
    public void fuelShouldNotBeFound(final FuelSpecification specification)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, NOT_FOUND);
        assertTrue(isNoSuchFuelError(actualResponse));
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
        return provideSuccessFuelSearchingArguments();
    }

    private static Stream<Arguments> notFoundFuelSearchingArgumentsProvider() {
        return provideNotFoundFuelSearchingArguments();
    }

    private static Stream<Arguments> notAcceptableFuelSearchingArgumentsProvider() {
        return provideNotAcceptableFuelSearchingArguments();
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
