package by.aurorasoft.fuelsearcher.testutil;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@UtilityClass
public final class FuelControllerRequestUtil {
    private static final String CONTROLLER_URL = "/fuel";

    private static final String TEMPLATE_REGEX_MESSAGE_ERROR = "\\{\"httpStatus\":\"%s\","
            + "\"message\":\"%s\","
            + "\"dateTime\":\"\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}\"}";

    private static final String REGEX_MESSAGE_ERROR_NO_SUCH_FUEL = TEMPLATE_REGEX_MESSAGE_ERROR.formatted(
            "NOT_FOUND", "Fuel with given properties doesn't exist"
    );

    private static final String REGEX_MESSAGE_ERROR_NOT_VALID_SPECIFICATION = TEMPLATE_REGEX_MESSAGE_ERROR.formatted(
            "NOT_ACCEPTABLE", "Not valid properties: (.+)"
    );
    private static final Pattern PATTERN_MESSAGE_ERROR_NOT_VALID_SPECIFICATION = compile(
            REGEX_MESSAGE_ERROR_NOT_VALID_SPECIFICATION
    );
    private static final int GROUP_NUMBER_FAILED_PROPERTY_NAMES = 1;
    private static final String SEPARATOR_FAILED_PROPERTY_NAMES = ", ";

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

    public static String doRequest(final MockMvc mockMvc,
                                   final FuelSpecification specification,
                                   final HttpStatus expectedHttpStatus)
            throws Exception {
        final RequestBuilder requestBuilder = createRequestBuilder(specification);
        return ControllerRequestUtil.doRequest(mockMvc, requestBuilder, expectedHttpStatus);
    }

    public static boolean isNoSuchFuelError(final String response) {
        return response.matches(REGEX_MESSAGE_ERROR_NO_SUCH_FUEL);
    }

    public static boolean isNotValidSpecificationError(final String response) {
        return response.matches(REGEX_MESSAGE_ERROR_NOT_VALID_SPECIFICATION);
    }

    public static Set<String> findFailedPropertyNames(final String response) {
        final String failedPropertyNamesPart = findFailedPropertyNamesPart(response);
        final String[] failedPropertyNames = failedPropertyNamesPart.split(SEPARATOR_FAILED_PROPERTY_NAMES);
        return Set.of(failedPropertyNames);
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

    private static String findFailedPropertyNamesPart(final String response) {
        final Matcher matcher = PATTERN_MESSAGE_ERROR_NOT_VALID_SPECIFICATION.matcher(response);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Given response isn't specification error. Given response: %s".formatted(response)
            );
        }
        return matcher.group(GROUP_NUMBER_FAILED_PROPERTY_NAMES);
    }
}
