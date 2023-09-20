package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public final class FilterPropertyMetadataSearcherTest {

    @Test
    public void propertyNameShouldBeFound() {
        final TestFilterPropertyMetadataSearcher givenSearcher = new TestFilterPropertyMetadataSearcher();

        final String givenPropertyName = "property-name";
        final TestFilter givenFilter = createFilter(givenPropertyName);

        final String actual = givenSearcher.findPropertyName(givenFilter);
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void allowableValuesShouldBeFound() {
        throw new RuntimeException();
    }

    @SuppressWarnings("SameParameterValue")
    private static TestFilter createFilter(final String propertyName) {
        final TestFilter givenFilter = Mockito.mock(TestFilter.class);
        when(givenFilter.findPropertyName()).thenReturn(propertyName);
        return givenFilter;
    }

    private static final class TestFilter extends Filter<Object> {

        public TestFilter() {
            super(null, MIN_VALUE);
        }

        @Override
        protected Object filter(final List<XWPFTableRow> rows,
                                final String filtrationValue,
                                final int filtrationCellIndex) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class TestFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<TestFilter> {
        private final List<XWPFTableRow> rowsWithPropertyValues;

        public TestFilterPropertyMetadataSearcher() {
            this(null);
        }

        public TestFilterPropertyMetadataSearcher(final List<XWPFTableRow> rowsWithPropertyValues) {
            super(TestFilter.class);
            this.rowsWithPropertyValues = rowsWithPropertyValues;
        }

        @Override
        protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                                  final TestFilter filter) {
            return this.rowsWithPropertyValues.stream();
        }
    }
}
