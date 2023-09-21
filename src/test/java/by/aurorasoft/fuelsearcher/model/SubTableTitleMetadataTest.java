package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public final class SubTableTitleMetadataTest {
    private static final String FIELD_NAME_TEMPLATE = "template";
    private static final String FIELD_NAME_ARGUMENT_EXTRACTORS = "argumentExtractors";

    @Test
    public void builderShouldBeCreated() {
        final SubTableTitleMetadataBuilder actual = SubTableTitleMetadata.builder();
        assertNotNull(actual);
    }

    @Test
    public void templateShouldBeAccumulatedByBuilder()
            throws Exception {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
        final String givenTemplate = "template";

        givenBuilder.template(givenTemplate);

        final String actual = findTemplate(givenBuilder);
        assertSame(givenTemplate, actual);
    }

    @Test
    public void argumentExtractorsShouldBeAccumulatedByBuilder() {
        throw new RuntimeException();
    }

    private static String findTemplate(final SubTableTitleMetadataBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_TEMPLATE,
                String.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findArgumentExtractors(
            final SubTableTitleMetadataBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_ARGUMENT_EXTRACTORS,
                List.class
        );
    }

    private static <P> P findProperty(final SubTableTitleMetadataBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                builder,
                fieldName,
                SubTableTitleMetadataBuilder.class,
                propertyType
        );
    }

    private static <S, P> P findProperty(final S source,
                                         final String fieldName,
                                         final Class<S> sourceType,
                                         final Class<P> propertyType)
            throws Exception {
        final Field field = sourceType.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(source);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }
}
