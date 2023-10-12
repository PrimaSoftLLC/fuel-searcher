package com.aurorasoft.fuelsearcher.it.fuelsearching;

import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.FuelSearchingArgumentsProvidingUtil;
import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest
@AutoConfigureMockMvc
public final class FuelSearchingIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("successFuelSearchingArgumentsProvider")
    public void fuelShouldBeFound(final FuelSpecification specification, final Fuel expected)
            throws Exception {
        this.testSearching(
                specification,
                OK,
                actualResponse -> this.isFuelSearchingSuccess(actualResponse, expected)
        );
    }

    @ParameterizedTest
    @MethodSource("notFoundFuelSearchingArgumentsProvider")
    public void fuelShouldNotBeFound(final FuelSpecification specification)
            throws Exception {
        this.testSearching(
                specification,
                NOT_FOUND,
                FuelControllerRequestUtil::isNoSuchFuelError
        );
    }

    @ParameterizedTest
    @MethodSource("notAcceptableFuelSearchingArgumentsProvider")
    public void fuelShouldNotBeFoundBecauseOfNotValidSpecification(final FuelSpecification specification,
                                                                   final Set<String> expectedFailedPropertyNames)
            throws Exception {
        this.testSearching(
                specification,
                NOT_ACCEPTABLE,
                actualResponse -> isCorrectNotValidSpecificationError(actualResponse, expectedFailedPropertyNames)
        );
    }

    private static Stream<Arguments> successFuelSearchingArgumentsProvider() {
        return FuelSearchingArgumentsProvidingUtil.provideSuccessFuelSearchingArguments();
    }

    private static Stream<Arguments> notFoundFuelSearchingArgumentsProvider() {
        return FuelSearchingArgumentsProvidingUtil.provideNotFoundFuelSearchingArguments();
    }

    private static Stream<Arguments> notAcceptableFuelSearchingArgumentsProvider() {
        return FuelSearchingArgumentsProvidingUtil.provideNotAcceptableFuelSearchingArguments();
    }

    private void testSearching(final FuelSpecification specification,
                               final HttpStatus expectedHttpStatus,
                               final Predicate<String> responsePredicate)
            throws Exception {
        final String actualResponse = doRequest(this.mockMvc, specification, expectedHttpStatus);
        final boolean testSuccess = responsePredicate.test(actualResponse);
        assertTrue(testSuccess);
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

    private static boolean isCorrectNotValidSpecificationError(final String actualResponse,
                                                               final Set<String> expectedFailedPropertyNames) {
        return isNotValidSpecificationError(actualResponse)
                && isCorrectFailedPropertyNames(actualResponse, expectedFailedPropertyNames);
    }

    private static boolean isCorrectFailedPropertyNames(final String actualResponse,
                                                        final Set<String> expectedFailedPropertyNames) {
        final Set<String> actualFailedPropertyNames = findFailedPropertyNames(actualResponse);
        return Objects.equals(expectedFailedPropertyNames, actualFailedPropertyNames);
    }
}
