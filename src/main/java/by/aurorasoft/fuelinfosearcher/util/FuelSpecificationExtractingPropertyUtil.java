package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.Specification;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public final class FuelSpecificationExtractingPropertyUtil {
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

    public static String extractTableName(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findTableName,
                PROPERTY_NAME_OF_TABLE_NAME
        );
    }

    public static String extractTractor(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findTractor,
                PROPERTY_NAME_OF_TRACTOR
        );
    }

    public static String extractMachinery(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findMachinery,
                PROPERTY_NAME_OF_MACHINERY
        );
    }

    public static String extractCorpusCount(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findCorpusCount,
                PROPERTY_NAME_OF_CORPUS_COUNT
        );
    }

    public static String extractPloughingDepth(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findPloughingDepth,
                PROPERTY_NAME_OF_PLOUGHING_DEPTH
        );
    }

    public static String extractRoutingLength(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findRoutingLength,
                PROPERTY_NAME_OF_ROUTING_LENGTH
        );
    }

    public static String extractSpecificResistance(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findSpecificResistance,
                PROPERTY_NAME_OF_SPECIFIC_RESISTANCE
        );
    }

    public static String extractSoilType(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findSoilType,
                PROPERTY_NAME_OF_SOIL_TYPE
        );
    }

    public static String extractProcessingDepth(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findProcessingDepth,
                PROPERTY_NAME_OF_PROCESSING_DEPTH
        );
    }

    public static String extractWorkingWidth(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findWorkingWidth,
                PROPERTY_NAME_OF_WORKING_WIDTH
        );
    }

    public static String extractSowingNorm(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findSowingNorm,
                PROPERTY_NAME_OF_SOWING_NORM
        );
    }

    public static String extractFertilizerType(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findFertilizerType,
                PROPERTY_NAME_OF_FERTILIZER_TYPE
        );
    }

    public static String extractChargingMethodAndTransportDistance(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findChargingMethodAndTransportDistance,
                PROPERTY_NAME_OF_CHARGING_METHOD_AND_TRANSPORT_DISTANCE
        );
    }

    public static String extractSpreadRate(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findSpreadRate,
                PROPERTY_NAME_OF_SPREAD_RATE
        );
    }

    public static String extractRoadGroup(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findRoadGroup,
                PROPERTY_NAME_OF_ROAD_GROUP
        );
    }

    public static String extractTransportDistance(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findTransportDistance,
                PROPERTY_NAME_OF_TRANSPORT_DISTANCE
        );
    }

    public static String extractCargoClass(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findCargoClass,
                PROPERTY_NAME_OF_CARGO_CLASS
        );
    }

    public static String extractYield(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findYield,
                PROPERTY_NAME_OF_YIELD
        );
    }

    public static String extractRowWidth(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findRowWidth,
                PROPERTY_NAME_OF_ROW_WIDTH
        );
    }

    public static String extractCombine(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findCombine,
                PROPERTY_NAME_OF_COMBINE
        );
    }

    public static String extractWeightRatioGrainToStraw(final Specification specification) {
        return extractProperty(
                specification,
                Specification::findWeightRatioGrainToStraw,
                PROPERTY_NAME_OF_WEIGHT_RATIO_GRAIN_TO_STRAW
        );
    }

    private static String extractProperty(final Specification specification,
                                          final Function<Specification, Optional<String>> propertyExtractor,
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
