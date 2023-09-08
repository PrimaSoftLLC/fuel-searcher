package by.aurorasoft.fuelsearcher.model.filter.factory;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

public final class FilterFactoryTest {

    @Test
    public void filterShouldBeCreated() {
        throw new RuntimeException();
    }

    private static final class TestFilterFactory extends FilterFactory<Filter<?>, SpecificationPropertyExtractor> {
        private final Filter<?> createdFilter;
        private SpecificationPropertyExtractor filterFiltrationValueExtractor;
        private int filterFiltrationCellIndex;

        public TestFilterFactory(final SpecificationPropertyExtractor filtrationValueExtractor,
                                 final Filter<?> createdFilter) {
            super(filtrationValueExtractor);
            this.createdFilter = createdFilter;
        }

        @Override
        protected Filter<?> create(final SpecificationPropertyExtractor filtrationValueExtractor,
                                   final int filtrationCellIndex) {
            this.filterFiltrationValueExtractor = filtrationValueExtractor;
            this.filterFiltrationCellIndex = filtrationCellIndex;
            return this.createdFilter;
        }
    }
}
