package by.aurorasoft.fuelsearcher.model.filter.factory;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FilterFactoryTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenPropertyName = "property";
        final SpecificationPropertyExtractor givenFiltrationValueExtractor = createFiltrationValueExtractor(
                givenPropertyName
        );
        final FilterFactory<?, ?> givenFactory = new TestFilterFactory(givenFiltrationValueExtractor, null);

        final String actual = givenFactory.findAlias();
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void filterShouldBeCreated() {
        final SpecificationPropertyExtractor givenFiltrationValueExtractor = mock(SpecificationPropertyExtractor.class);
        final Filter<?> givenCreatedFilter = mock(Filter.class);
        final TestFilterFactory givenFactory = new TestFilterFactory(
                givenFiltrationValueExtractor,
                givenCreatedFilter
        );

        final int givenFiltrationCellIndex = 3;
        final Filter<?> actual = givenFactory.create(givenFiltrationCellIndex);
        assertSame(givenCreatedFilter, actual);
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createFiltrationValueExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }

    private static final class TestFilterFactory extends FilterFactory<Filter<?>, SpecificationPropertyExtractor> {
        private final Filter<?> createdFilter;

        public TestFilterFactory(final SpecificationPropertyExtractor filtrationValueExtractor,
                                 final Filter<?> createdFilter) {
            super(filtrationValueExtractor);
            this.createdFilter = createdFilter;
        }

        @Override
        protected Filter<?> create(final SpecificationPropertyExtractor filtrationValueExtractor,
                                   final int filtrationCellIndex) {
            return this.createdFilter;
        }
    }
}
