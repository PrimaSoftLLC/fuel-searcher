package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public final class FuelInfoSpecificationUtil {
    private static final String PROPERTY_NAME_OF_TABLE_NAME = "tableName";
    private static final String PROPERTY_NAME_OF_TRACTOR = "tractor";
    private static final String PROPERTY_NAME_OF_PLOUGH_MARK = "ploughMark";
    private static final String PROPERTY_NAME_OF_CORPUS_COUNT = "corpusCount";
    private static final String PROPERTY_NAME_OF_PLOUGHING_DEPTH = "ploughingDepth";
    private static final String PROPERTY_NAME_OF_ROUTING_LENGTH = "routingLength";
    private static final String PROPERTY_NAME_OF_SPECIFIC_RESISTANCE = "specificResistance";
    private static final String PROPERTY_NAME_OF_SOIL_TYPE = "soilType";

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

    public static String extractPloughMark(final FuelInfoSpecification specification) {
        return extractProperty(
                specification,
                FuelInfoSpecification::findPloughMark,
                PROPERTY_NAME_OF_PLOUGH_MARK
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
