package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class CompositeFuelSearcherTest {

    @Test
    public void builderShouldBeCreated() {
        final CompositeSearcherBuilder actual = CompositeFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound() {
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);
        final String givenSubTableTitleTemplate = "%s and %s";

        final String firstGivenProperty = "first-property";
        final SpecificationPropertyExtractor firstGivenSubTableTitleTemplateArgumentExtractor = createPropertyExtractor(
                firstGivenProperty, givenSpecification
        );

        final String secondGivenProperty = "second-property";
        final SpecificationPropertyExtractor secondGivenSubTableTitleTemplateArgumentExtractor = createPropertyExtractor(
                secondGivenProperty, givenSpecification
        );

        throw new RuntimeException();
    }

    private static CompositeFuelSearcher createSearcher(final String subTableTitleTemplate,
                                                        final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors)
            throws Exception {
        final Constructor<CompositeFuelSearcher> constructor = CompositeFuelSearcher.class.getDeclaredConstructor(
                FuelTable.class,
                Map.class,
                FilterChain.class,
                SpecificationPropertyExtractor.class,
                String.class,
                List.class
        );
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(
                    null,
                    null,
                    null,
                    null,
                    subTableTitleTemplate,
                    subTableTitleTemplateArgumentExtractors
            );
        } finally {
            constructor.setAccessible(false);
        }
    }

    private static SpecificationPropertyExtractor createPropertyExtractor(final String property,
                                                                          final FuelSpecification specification) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.extract(same(specification))).thenReturn(property);
        return extractor;
    }
}
