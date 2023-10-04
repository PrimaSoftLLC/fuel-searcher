package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.junit.Test;

import java.util.List;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SpecificationValidatorsFactoryTest {
    private static final String FIELD_NAME_TABLE_NAME = "tableName";
    private static final String FIELD_NAME_REQUIRED_PROPERTY_EXTRACTORS = "requiredPropertyExtractors";

    private final SpecificationValidatorsFactory factory = new SpecificationValidatorsFactory(null);

    @Test
    public void validatorShouldBeCreated() {
        final String givenTableName = "table-name";
        final List<SpecificationPropertyExtractor> givenRequiredPropertyExtractors = List.of(
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class)
        );
        final FuelSearcher givenSearcher = createSearcher(givenTableName, givenRequiredPropertyExtractors);

        final SpecificationValidator actual = this.factory.createDerivedObject(givenSearcher);

        final String actualTableName = findTableName(actual);
        assertSame(givenTableName, actualTableName);

        final List<SpecificationPropertyExtractor> actualRequiredPropertyExtractors = findRequiredPropertyExtractors(
                actual
        );
        assertSame(givenRequiredPropertyExtractors, actualRequiredPropertyExtractors);
    }

    @SuppressWarnings("SameParameterValue")
    private static FuelSearcher createSearcher(final String tableName,
                                               final List<SpecificationPropertyExtractor> givenPropertyExtractors) {
        final FuelSearcher givenSearcher = mock(FuelSearcher.class);
        when(givenSearcher.findTableName()).thenReturn(tableName);
        when(givenSearcher.findUsedPropertyExtractors()).thenReturn(givenPropertyExtractors);
        return givenSearcher;
    }

    private static String findTableName(final SpecificationValidator validator) {
        return findProperty(
                validator,
                FIELD_NAME_TABLE_NAME,
                String.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findRequiredPropertyExtractors(
            final SpecificationValidator validator
    ) {
        return findProperty(
                validator,
                FIELD_NAME_REQUIRED_PROPERTY_EXTRACTORS,
                List.class
        );
    }
}
