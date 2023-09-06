package by.aurorasoft.fuelsearcher.testutil;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;
import java.util.function.Function;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@UtilityClass
public final class FuelRequestBuilderUtil {
    private static final String CONTROLLER_URL = "/fuel";

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

    public static MockHttpServletRequestBuilder createRequestBuilder(final FuelSpecification specification) {
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

}
