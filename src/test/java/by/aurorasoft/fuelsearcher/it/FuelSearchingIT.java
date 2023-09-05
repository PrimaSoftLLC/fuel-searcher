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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public final class FuelSearchingIT extends AbstractContextTest {
    private static final String CONTROLLER_URL = "/fuel";
    private static final String EXPECTED_CONTENT_TYPE = "application/json";
    private static final String EXPECTED_REGEX_MESSAGE_ERROR_NO_SUCH_FUEL = "\\{\"httpStatus\":\"NOT_FOUND\","
            + "\"message\":\"Fuel with given properties doesn't exist\","
            + "\"dateTime\":\"\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}\"}";

    private static final String PARAM_NAME_TABLE_NAME = "tableName";
    private static final String PARAM_NAME_TRACTOR = "tractor";
    private static final String PARAM_NAME_MACHINERY = "machinery";
    private static final String PARAM_NAME_CORPUS_COUNT = "corpusCount";
    private static final String PARAM_NAME_PLOUGHING_DEPTH = "ploughingDepth";
    private static final String PARAM_NAME_ROUTING_LENGTH = "routingLength";
    private static final String PARAM_NAME_SPECIFIC_RESISTANCE = "specificResistance";
    private static final String PARAM_NAME_SOIL_TYPE = "soilType";
    private static final String PARAM_NAME_PROCESSING_DEPTH = "processingDepth";
    private static final String PARAM_NAME_WORKING_WIDTH = "workingWidth";
    private static final String PARAM_NAME_SOWING_NORM = "sowingNorm";
    private static final String PARAM_NAME_FERTILIZER_TYPE = "fertilizerType";
    private static final String PARAM_NAME_CHARGING_METHOD_AND_TRANSPORT_DISTANCE = "chargingMethodAndTransportDistance";
    private static final String PARAM_NAME_SPREAD_RATE = "spreadRate";
    private static final String PARAM_NAME_ROAD_GROUP = "roadGroup";
    private static final String PARAM_NAME_TRANSPORT_DISTANCE = "transportDistance";
    private static final String PARAM_NAME_CARGO_CLASS = "cargoClass";
    private static final String PARAM_NAME_YIELD = "yield";
    private static final String PARAM_NAME_ROW_WIDTH = "rowWidth";
    private static final String PARAM_NAME_COMBINE = "combine";
    private static final String PARAM_NAME_WEIGHT_RATIO_GRAIN_TO_STRAW = "weightRatioGrainToStraw";

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
            new TwentyFifthTableFuelSearchingArgumentsProvider()
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
        final String actualResponse = this.doRequest(specification, expectedHttpStatus);
        final boolean testSuccess = optionalExpectedFuel
                .map(expectedFuel -> this.isFuelSearchingSuccess(actualResponse, expectedFuel))
                .orElseGet(() -> isNoSuchFuelError(actualResponse));
        assertTrue(testSuccess);
    }

    private static Stream<Arguments> fuelSearchingArgumentsProvider() {
        return ARGUMENTS_PROVIDERS.stream().flatMap(TableFuelSearchingArgumentsProvider::provide);
    }

    private String doRequest(final FuelSpecification specification, final HttpStatus expectedHttpStatus)
            throws Exception {
        return this.mockMvc.perform(createRequestBuilder(specification))
                .andExpect(status().is(expectedHttpStatus.value()))
                .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private static MockHttpServletRequestBuilder createRequestBuilder(final FuelSpecification specification) {
        final MockHttpServletRequestBuilder requestBuilder = get(CONTROLLER_URL);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findTableName, PARAM_NAME_TABLE_NAME);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findTractor, PARAM_NAME_TRACTOR);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findMachinery, PARAM_NAME_MACHINERY);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findCorpusCount, PARAM_NAME_CORPUS_COUNT);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findPloughingDepth, PARAM_NAME_PLOUGHING_DEPTH);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findRoutingLength, PARAM_NAME_ROUTING_LENGTH);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findSpecificResistance, PARAM_NAME_SPECIFIC_RESISTANCE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findSoilType, PARAM_NAME_SOIL_TYPE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findProcessingDepth, PARAM_NAME_PROCESSING_DEPTH);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findWorkingWidth, PARAM_NAME_WORKING_WIDTH);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findSowingNorm, PARAM_NAME_SOWING_NORM);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findFertilizerType, PARAM_NAME_FERTILIZER_TYPE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findChargingMethodAndTransportDistance, PARAM_NAME_CHARGING_METHOD_AND_TRANSPORT_DISTANCE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findSpreadRate, PARAM_NAME_SPREAD_RATE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findRoadGroup, PARAM_NAME_ROAD_GROUP);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findTransportDistance, PARAM_NAME_TRANSPORT_DISTANCE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findCargoClass, PARAM_NAME_CARGO_CLASS);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findYield, PARAM_NAME_YIELD);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findRowWidth, PARAM_NAME_ROW_WIDTH);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findCombine, PARAM_NAME_COMBINE);
        appendPropertyAsParamIfExist(requestBuilder, specification, FuelSpecification::findWeightRatioGrainToStraw, PARAM_NAME_WEIGHT_RATIO_GRAIN_TO_STRAW);
        return requestBuilder;
    }

    private static void appendPropertyAsParamIfExist(final MockHttpServletRequestBuilder requestBuilder,
                                                     final FuelSpecification specification,
                                                     final Function<FuelSpecification, Optional<String>> propertyExtractor,
                                                     final String paramName) {
        final Optional<String> optionalProperty = propertyExtractor.apply(specification);
        optionalProperty.ifPresent(property -> requestBuilder.param(paramName, property));
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
