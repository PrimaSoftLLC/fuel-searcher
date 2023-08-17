package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
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

    public static String extractTableName(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findTableName,
                PROPERTY_NAME_OF_TABLE_NAME
        );
    }

    public static String extractTractor(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findTractor,
                PROPERTY_NAME_OF_TRACTOR
        );
    }

    public static String extractMachinery(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findMachinery,
                PROPERTY_NAME_OF_MACHINERY
        );
    }

    public static String extractCorpusCount(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findCorpusCount,
                PROPERTY_NAME_OF_CORPUS_COUNT
        );
    }

    public static String extractPloughingDepth(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findPloughingDepth,
                PROPERTY_NAME_OF_PLOUGHING_DEPTH
        );
    }

    public static String extractRoutingLength(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findRoutingLength,
                PROPERTY_NAME_OF_ROUTING_LENGTH
        );
    }

    public static String extractSpecificResistance(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findSpecificResistance,
                PROPERTY_NAME_OF_SPECIFIC_RESISTANCE
        );
    }

    public static String extractSoilType(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findSoilType,
                PROPERTY_NAME_OF_SOIL_TYPE
        );
    }

    public static String extractProcessingDepth(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findProcessingDepth,
                PROPERTY_NAME_OF_PROCESSING_DEPTH
        );
    }

    public static String extractWorkingWidth(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findWorkingWidth,
                PROPERTY_NAME_OF_WORKING_WIDTH
        );
    }

    public static String extractSowingNorm(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findSowingNorm,
                PROPERTY_NAME_OF_SOWING_NORM
        );
    }

    public static String extractFertilizerType(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findFertilizerType,
                PROPERTY_NAME_OF_FERTILIZER_TYPE
        );
    }

    public static String extractChargingMethodAndTransportDistance(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findChargingMethodAndTransportDistance,
                PROPERTY_NAME_OF_CHARGING_METHOD_AND_TRANSPORT_DISTANCE
        );
    }

    public static String extractSpreadRate(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findSpreadRate,
                PROPERTY_NAME_OF_SPREAD_RATE
        );
    }

    public static String extractRoadGroup(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findRoadGroup,
                PROPERTY_NAME_OF_ROAD_GROUP
        );
    }

    public static String extractTransportDistance(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findTransportDistance,
                PROPERTY_NAME_OF_TRANSPORT_DISTANCE
        );
    }

    public static String extractCargoClass(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findCargoClass,
                PROPERTY_NAME_OF_CARGO_CLASS
        );
    }

    public static String extractYield(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findYield,
                PROPERTY_NAME_OF_YIELD
        );
    }

    public static String extractRowWidth(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findRowWidth,
                PROPERTY_NAME_OF_ROW_WIDTH
        );
    }

    public static String extractCombine(final FuelSpecification specification) {
        return extractProperty(
                specification,
                FuelSpecification::findCombine,
                PROPERTY_NAME_OF_COMBINE
        );
    }

    private static String extractProperty(final FuelSpecification specification,
                                          final Function<FuelSpecification, Optional<String>> propertyExtractor,
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
