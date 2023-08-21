package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public final class FuelInfoSpecificationUtil {
    private static final String PROPERTY_NAME_OF_TABLE_NAME = "tableName";
    private static final String PROPERTY_NAME_OF_TRACTOR = "tractor";
    private static final String PROPERTY_NAME_OF_MACHINERY = "ploughMark";
    private static final String PROPERTY_NAME_OF_CORPUS_COUNT = "corpusCount";
    private static final String PROPERTY_NAME_OF_PLOUGHING_DEPTH = "ploughingDepth";
    private static final String PROPERTY_NAME_OF_ROUTING_LENGTH = "routingLength";
    private static final String PROPERTY_NAME_OF_SPECIFIC_RESISTANCE = "specificResistance";
    private static final String PROPERTY_NAME_OF_SOIL_TYPE = "soilType";
    private static final String PROPERTY_NAME_OF_PROCESSING_DEPTH = "processingDepth";
    private static final String PROPERTY_NAME_OF_WORKING_WIDTH = "workingWidth";
    private static final String PROPERTY_NAME_OF_SOWING_NORM = "sowingNorm";
    private static final String PROPERTY_NAME_OF_FERTILIZER_TYPE = "fertilizerType";
    private static final String PROPERTY_NAME_OF_CHARGING_METHOD_AND_TRANSPORT_DISTANCE
            = "chargingMethodAndTransportDistance";
    private static final String PROPERTY_NAME_OF_SPREAD_RATE = "spreadRate";
    private static final String PROPERTY_NAME_OF_ROAD_GROUP = "roadGroup";
    private static final String PROPERTY_NAME_OF_TRANSPORT_DISTANCE = "transportDistance";
    private static final String PROPERTY_NAME_OF_CARGO_CLASS = "cargoClass";
    private static final String PROPERTY_NAME_OF_YIELD = "yield";
    private static final String PROPERTY_NAME_OF_ROW_WIDTH = "rowWidth";
    private static final String PROPERTY_NAME_OF_COMBINE = "combine";
    private static final String PROPERTY_NAME_OF_WEIGHT_RATIO_GRAIN_TO_STRAW = "weightRatioGrainToStraw";

    public static String extractTableName(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findTableName,
                PROPERTY_NAME_OF_TABLE_NAME
        );
    }

    public static String extractTractor(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findTractor,
                PROPERTY_NAME_OF_TRACTOR
        );
    }

    public static String extractMachinery(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findMachinery,
                PROPERTY_NAME_OF_MACHINERY
        );
    }

    public static String extractCorpusCount(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findCorpusCount,
                PROPERTY_NAME_OF_CORPUS_COUNT
        );
    }

    public static String extractPloughingDepth(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findPloughingDepth,
                PROPERTY_NAME_OF_PLOUGHING_DEPTH
        );
    }

    public static String extractRoutingLength(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findRoutingLength,
                PROPERTY_NAME_OF_ROUTING_LENGTH
        );
    }

    public static String extractSpecificResistance(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findSpecificResistance,
                PROPERTY_NAME_OF_SPECIFIC_RESISTANCE
        );
    }

    public static String extractSoilType(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findSoilType,
                PROPERTY_NAME_OF_SOIL_TYPE
        );
    }

    public static String extractProcessingDepth(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findProcessingDepth,
                PROPERTY_NAME_OF_PROCESSING_DEPTH
        );
    }

    public static String extractWorkingWidth(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findWorkingWidth,
                PROPERTY_NAME_OF_WORKING_WIDTH
        );
    }

    public static String extractSowingNorm(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findSowingNorm,
                PROPERTY_NAME_OF_SOWING_NORM
        );
    }

    public static String extractFertilizerType(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findFertilizerType,
                PROPERTY_NAME_OF_FERTILIZER_TYPE
        );
    }

    public static String extractChargingMethodAndTransportDistance(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findChargingMethodAndTransportDistance,
                PROPERTY_NAME_OF_CHARGING_METHOD_AND_TRANSPORT_DISTANCE
        );
    }

    public static String extractSpreadRate(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findSpreadRate,
                PROPERTY_NAME_OF_SPREAD_RATE
        );
    }

    public static String extractRoadGroup(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findRoadGroup,
                PROPERTY_NAME_OF_ROAD_GROUP
        );
    }

    public static String extractTransportDistance(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findTransportDistance,
                PROPERTY_NAME_OF_TRANSPORT_DISTANCE
        );
    }

    public static String extractCargoClass(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findCargoClass,
                PROPERTY_NAME_OF_CARGO_CLASS
        );
    }

    public static String extractYield(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findYield,
                PROPERTY_NAME_OF_YIELD
        );
    }

    public static String extractRowWidth(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findRowWidth,
                PROPERTY_NAME_OF_ROW_WIDTH
        );
    }

    public static String extractCombine(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findCombine,
                PROPERTY_NAME_OF_COMBINE
        );
    }

    public static String extractWeightRatioGrainToStraw(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findWeightRatioGrainToStraw,
                PROPERTY_NAME_OF_WEIGHT_RATIO_GRAIN_TO_STRAW
        );
    }

    private static String extractProperty(final FuelInfoSpecification specification,
                                          final Function<FuelInfoSpecification, Optional<String>> propertyExtractor,
                                          final String propertyName) {
        final Optional<String> optionalProperty = propertyExtractor.apply(specification);
        return optionalProperty.orElseThrow(
                () -> new FuelSpecificationPropertyExtractingException(
                        "Specification doesn't have property '%s'".formatted(propertyName)
                )
        );
    }

    private static final class FuelSpecificationPropertyExtractingException extends RuntimeException {

        public FuelSpecificationPropertyExtractingException() {

        }

        public FuelSpecificationPropertyExtractingException(final String description) {
            super(description);
        }

        public FuelSpecificationPropertyExtractingException(final Exception cause) {
            super(cause);
        }

        public FuelSpecificationPropertyExtractingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }

}
