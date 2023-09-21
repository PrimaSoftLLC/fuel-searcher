package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.util.XWPFContentUtil;
import by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellText;
import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

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
    @SuppressWarnings("unchecked")
    public void allowableValuesShouldBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class);
             final MockedStatic<XWPFContentUtil> mockedContentUtil = mockStatic(XWPFContentUtil.class)) {
            final TestFilterPropertyMetadataSearcher givenSearcher = new TestFilterPropertyMetadataSearcher();

            final int givenFiltrationCellIndex = 3;
            final TestFilter givenFilter = createFilter(givenFiltrationCellIndex);

            final XWPFParagraph givenParagraph = mock(XWPFParagraph.class);

            final XWPFTableRow firstGivenRow = createRowWithContentInCell(
                    "content-1",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow secondGivenRow = createRowWithContentInCell(
                    "content-2",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow thirdGivenRow = createRowWithContentInCell(
                    "content-3",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow fourthGivenRow = createRowWithContentInCell(
                    "content-4",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow fifthGivenRow = createRowWithContentInCell(
                    "content-5",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow sixthGivenRow = createRowWithContentInCell(
                    "conTE   nt-5",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow seventhGivenRow = createRowWithContentInCell(
                    "content-6",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow eighthGivenRow = createRowWithContentInCell(
                    "content-6",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final XWPFTableRow ninthGivenRow = createRowWithContentInCell(
                    "content-7",
                    givenFiltrationCellIndex,
                    mockedRowUtil
            );
            final List<XWPFTableRow> givenRows = List.of(
                    firstGivenRow,
                    secondGivenRow,
                    thirdGivenRow,
                    fourthGivenRow,
                    fifthGivenRow,
                    sixthGivenRow,
                    seventhGivenRow,
                    eighthGivenRow,
                    ninthGivenRow
            );
            final XWPFTable givenSubTable = createSubTable(givenRows);

            final String[] givenAllowableValues = {"content-5", "content-6", "content-7"};
            mockedContentUtil.when(
                    () -> removeDuplicatesIgnoringWhitespacesAndCase(any(Stream.class))
            ).thenReturn(givenAllowableValues);

            final List<IBodyElement> givenTableElements = List.of(givenParagraph, givenSubTable);

            final String[] actual = givenSearcher.findAllowableValues(givenTableElements, givenFilter);
            assertArrayEquals(givenAllowableValues, actual);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static TestFilter createFilter(final String propertyName) {
        return createObjectWithProperty(
                TestFilter.class,
                propertyName,
                Filter::findPropertyName
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static TestFilter createFilter(final int filtrationCellIndex) {
        return createObjectWithProperty(
                TestFilter.class,
                filtrationCellIndex,
                Filter::getFiltrationCellIndex
        );
    }

    private static XWPFTable createSubTable(final List<XWPFTableRow> rows) {
        return createObjectWithProperty(
                XWPFTable.class,
                rows,
                XWPFTable::getRows
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableRow createRowWithContentInCell(final String content,
                                                           final int cellIndex,
                                                           final MockedStatic<XWPFTableRowUtil> mockedRowUtil) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(() -> extractCellText(same(row), eq(cellIndex))).thenReturn(content);
        return row;
    }

    private static <T, P> T createObjectWithProperty(final Class<T> objectType,
                                                     final P propertyValue,
                                                     final Function<T, P> propertyExtractor) {
        final T object = mock(objectType);
        when(propertyExtractor.apply(object)).thenReturn(propertyValue);
        return object;
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

        public TestFilterPropertyMetadataSearcher() {
            super(TestFilter.class);
        }

        @Override
        protected Stream<XWPFTableRow> findRowsWithAllowableValues(final List<XWPFTableRow> subTableDataRows,
                                                                   final TestFilter filter) {
            return subTableDataRows.stream();
        }
    }
}
