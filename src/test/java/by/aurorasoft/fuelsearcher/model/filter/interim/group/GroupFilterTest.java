//package by.aurorasoft.fuelsearcher.model.filter.interim.group;
//
//import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.junit.Test;
//import org.mockito.MockedStatic;
//
//import java.util.List;
//
//import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsByGroup;
//import static java.lang.Integer.MIN_VALUE;
//import static org.junit.Assert.assertSame;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.mockStatic;
//
//public final class GroupFilterTest {
//
//    @Test
//    public void rowsShouldBeFiltered() {
//        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedUtil = mockStatic(XWPFTableRowFilteringUtil.class)) {
//            final String givenGroupValueRegex = "group-value-regex";
//            final TestGroupFilter givenFilter = new TestGroupFilter(givenGroupValueRegex);
//
//            final List<XWPFTableRow> givenRows = List.of(
//                    mock(XWPFTableRow.class),
//                    mock(XWPFTableRow.class),
//                    mock(XWPFTableRow.class)
//            );
//            final String givenGroupValue = "group-value";
//            final int givenGroupValueCellIndex = 2;
//
//            final List<XWPFTableRow> expected = List.of(mock(XWPFTableRow.class), mock(XWPFTableRow.class));
//            mockedUtil.when(
//                    () -> findRowsByGroup(
//                            same(givenRows),
//                            same(givenGroupValue),
//                            same(givenGroupValueRegex),
//                            eq(givenGroupValueCellIndex)
//                    )
//            ).thenReturn(expected);
//
//            final List<XWPFTableRow> actual = givenFilter.filter(givenRows, givenGroupValue, givenGroupValueCellIndex);
//            assertSame(expected, actual);
//        }
//    }
//
//    private static final class TestGroupFilter extends GroupFilter {
//        private final String groupValueRegex;
//
//        public TestGroupFilter(final String groupValueRegex) {
//            super(null, MIN_VALUE);
//            this.groupValueRegex = groupValueRegex;
//        }
//
//        @Override
//        protected String findGroupValueRegex() {
//            return this.groupValueRegex;
//        }
//    }
//}
