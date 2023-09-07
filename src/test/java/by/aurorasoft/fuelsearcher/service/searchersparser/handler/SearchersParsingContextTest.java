package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator.SpecificationValidatorBuilder;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void fuelTableShouldBeAccumulatedToSimpleSearcherBuilder()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setContextSearcherBuilder(givenContext, givenSearcherBuilder);

        final SpecificationValidatorBuilder givenSpecificationValidatorBuilder = mock(
                SpecificationValidatorBuilder.class
        );
        setContextSpecificationValidatorBuilder(givenContext, givenSpecificationValidatorBuilder);

        final String givenTableName = "table-name";
        final FuelTable givenFuelTable = createFuelTable(givenTableName);

        givenContext.accumulateFuelTable(givenFuelTable);

        verify(givenSpecificationValidatorBuilder, times(1)).tableName(same(givenTableName));
        verify(givenSearcherBuilder, times(1)).table(same(givenFuelTable));
    }

    @Test
    public void fuelTableShouldBeAccumulatedToCompositeSearcherBuilder()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setContextSearcherBuilder(givenContext, givenSearcherBuilder);

        final SpecificationValidatorBuilder givenSpecificationValidatorBuilder = mock(
                SpecificationValidatorBuilder.class
        );
        setContextSpecificationValidatorBuilder(givenContext, givenSpecificationValidatorBuilder);

        final String givenTableName = "table-name";
        final FuelTable givenFuelTable = createFuelTable(givenTableName);

        givenContext.accumulateFuelTable(givenFuelTable);

        verify(givenSpecificationValidatorBuilder, times(1)).tableName(same(givenTableName));
        verify(givenSearcherBuilder, times(1)).table(same(givenFuelTable));
    }

    @Test
    public void simpleSearcherShouldBeBuilt()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SpecificationValidatorBuilder givenSpecificationValidatorBuilder = mock(
                SpecificationValidatorBuilder.class
        );
        setContextSpecificationValidatorBuilder(givenContext, givenSpecificationValidatorBuilder);

        final SpecificationValidator givenSpecificationValidator = mock(SpecificationValidator.class);
        when(givenSpecificationValidatorBuilder.build()).thenReturn(givenSpecificationValidator);

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setContextSearcherBuilder(givenContext, givenSearcherBuilder);

        final SimpleFuelSearcher givenSearcher = mock(SimpleFuelSearcher.class);
        when(givenSearcherBuilder.build()).thenReturn(givenSearcher);

        givenContext.buildSimpleSearcher();

        final List<SpecificationValidator> actualSpecificationValidators = findSpecificationValidators(givenContext);
        final List<SpecificationValidator> expectedSpecificationValidators = List.of(givenSpecificationValidator);
        assertEquals(expectedSpecificationValidators, actualSpecificationValidators);

        final SpecificationValidatorBuilder actualSpecificationValidatorBuilder = findSpecificationValidatorBuilder(
                givenContext
        );
        assertNull(actualSpecificationValidatorBuilder);

        final List<FuelSearcher> actualSearchers = findSearchers(givenContext);
        final List<FuelSearcher> expectedSearchers = List.of(givenSearcher);
        assertEquals(expectedSearchers, actualSearchers);

        final SimpleSearcherBuilder actualSimpleSearcherBuilder = findSimpleSearcherBuilder(givenContext);
        assertNull(actualSimpleSearcherBuilder);
    }

    @Test
    public void compositeSearcherShouldBeBuilt()
            throws Exception {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SpecificationValidatorBuilder givenSpecificationValidatorBuilder = mock(
                SpecificationValidatorBuilder.class
        );
        setContextSpecificationValidatorBuilder(givenContext, givenSpecificationValidatorBuilder);

        final SpecificationValidator givenSpecificationValidator = mock(SpecificationValidator.class);
        when(givenSpecificationValidatorBuilder.build()).thenReturn(givenSpecificationValidator);

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setContextSearcherBuilder(givenContext, givenSearcherBuilder);

        final CompositeFuelSearcher givenSearcher = mock(CompositeFuelSearcher.class);
        when(givenSearcherBuilder.build()).thenReturn(givenSearcher);

        givenContext.buildCompositeSearcher();

        final List<SpecificationValidator> actualSpecificationValidators = findSpecificationValidators(givenContext);
        final List<SpecificationValidator> expectedSpecificationValidators = List.of(givenSpecificationValidator);
        assertEquals(expectedSpecificationValidators, actualSpecificationValidators);

        final SpecificationValidatorBuilder actualSpecificationValidatorBuilder = findSpecificationValidatorBuilder(
                givenContext
        );
        assertNull(actualSpecificationValidatorBuilder);

        final List<FuelSearcher> actualSearchers = findSearchers(givenContext);
        final List<FuelSearcher> expectedSearchers = List.of(givenSearcher);
        assertEquals(expectedSearchers, actualSearchers);

        final CompositeSearcherBuilder actualCompositeSearcherBuilder = findCompositeSearcherBuilder(givenContext);
        assertNull(actualCompositeSearcherBuilder);
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

    private static void setContextSearcherBuilder(final SearchersParsingContext context,
                                                  final SimpleSearcherBuilder builder)
            throws Exception {
        setContextProperty(context, FIELD_NAME_SIMPLE_SEARCHER_BUILDER, builder);
    }

    private static void setContextSearcherBuilder(final SearchersParsingContext context,
                                                  final CompositeSearcherBuilder builder)
            throws Exception {
        setContextProperty(context, FIELD_NAME_COMPOSITE_SEARCHER_BUILDER, builder);
    }

    private static void setContextSpecificationValidatorBuilder(final SearchersParsingContext context,
                                                                final SpecificationValidatorBuilder builder)
            throws Exception {
        setContextProperty(context, FIELD_NAME_SPECIFICATION_VALIDATOR_BUILDER, builder);
    }

    private static <T> void setContextProperty(final SearchersParsingContext context,
                                               final String fieldName,
                                               final T value)
            throws Exception {
        final Field field = SearchersParsingContext.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            field.set(context, value);
        } finally {
            field.setAccessible(false);
        }
    }

    private static FuelTable createFuelTable(final String tableName) {
        final FuelTable fuelTable = mock(FuelTable.class);
        when(fuelTable.getName()).thenReturn(tableName);
        return fuelTable;
    }
}
