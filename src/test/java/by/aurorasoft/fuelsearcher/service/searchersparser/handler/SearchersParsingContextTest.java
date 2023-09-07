package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator.SpecificationValidatorBuilder;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public final class SearchersParsingContextTest {
    private static final String FIELD_NAME_SEARCHERS = "searchers";
    private static final String FIELD_NAME_SPECIFICATION_VALIDATORS = "specificationValidators";
    private static final String FIELD_NAME_SIMPLE_SEARCHER_BUILDER = "simpleSearcherBuilder";
    private static final String FIELD_NAME_COMPOSITE_SEARCHER_BUILDER = "compositeSearcherBuilder";
    private static final String FIELD_NAME_SPECIFICATION_VALIDATOR_BUILDER = "specificationValidatorBuilder";

    @Test
    public void contextShouldBeCreated()
            throws Exception {
        final SearchersParsingContext actual = new SearchersParsingContext();

        final List<FuelSearcher> actualSearchers = findSearchers(actual);
        assertTrue(actualSearchers.isEmpty());

        final List<SpecificationValidator> actualSpecificationValidators = findSpecificationValidators(actual);
        assertTrue(actualSpecificationValidators.isEmpty());

        final SimpleSearcherBuilder actualSimpleSearcherBuilder = findSimpleSearcherBuilder(actual);
        assertNull(actualSimpleSearcherBuilder);

        final CompositeSearcherBuilder actualCompositeSearcherBuilder = findCompositeSearcherBuilder(actual);
        assertNull(actualCompositeSearcherBuilder);

        final SpecificationValidatorBuilder actualSpecificationValidatorBuilder = findSpecificationValidatorBuilder(
                actual
        );
        assertNull(actualSpecificationValidatorBuilder);
    }

    @Test
    public void parsingSimpleSearcherShouldBeStarted()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.startParseSimpleSearcher();

        final SimpleSearcherBuilder simpleSearcherBuilder = findSimpleSearcherBuilder(givenContext);
        assertNotNull(simpleSearcherBuilder);

        final SpecificationValidatorBuilder specificationValidatorBuilder = findSpecificationValidatorBuilder(
                givenContext
        );
        assertNotNull(specificationValidatorBuilder);
    }

    @Test
    public void parsingCompositeSearcherShouldBeStarted()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.startParseCompositeSearcher();

        final CompositeSearcherBuilder compositeSearcherBuilder = findCompositeSearcherBuilder(givenContext);
        assertNotNull(compositeSearcherBuilder);

        final SpecificationValidatorBuilder specificationValidatorBuilder = findSpecificationValidatorBuilder(
                givenContext
        );
        assertNotNull(specificationValidatorBuilder);
    }



    @SuppressWarnings("unchecked")
    private static List<FuelSearcher> findSearchers(final SearchersParsingContext context)
            throws Exception {
        return findContextProperty(
                context,
                FIELD_NAME_SEARCHERS,
                List.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationValidator> findSpecificationValidators(final SearchersParsingContext context)
            throws Exception {
        return findContextProperty(
                context,
                FIELD_NAME_SPECIFICATION_VALIDATORS,
                List.class
        );
    }

    private static SimpleSearcherBuilder findSimpleSearcherBuilder(final SearchersParsingContext context)
            throws Exception {
        return findContextProperty(
                context,
                FIELD_NAME_SIMPLE_SEARCHER_BUILDER,
                SimpleSearcherBuilder.class
        );
    }

    private static CompositeSearcherBuilder findCompositeSearcherBuilder(final SearchersParsingContext context)
            throws Exception {
        return findContextProperty(
                context,
                FIELD_NAME_COMPOSITE_SEARCHER_BUILDER,
                CompositeSearcherBuilder.class
        );
    }

    private static SpecificationValidatorBuilder findSpecificationValidatorBuilder(final SearchersParsingContext context)
            throws Exception {
        return findContextProperty(
                context,
                FIELD_NAME_SPECIFICATION_VALIDATOR_BUILDER,
                SpecificationValidatorBuilder.class
        );
    }

    private static <T> T findContextProperty(final SearchersParsingContext context,
                                             final String fieldName,
                                             final Class<T> propertyType)
            throws Exception {
        final Field field = SearchersParsingContext.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(context);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }
}
