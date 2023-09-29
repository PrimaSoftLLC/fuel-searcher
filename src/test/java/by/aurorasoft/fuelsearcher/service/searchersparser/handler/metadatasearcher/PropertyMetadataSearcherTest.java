//package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;
//
//import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
//import by.aurorasoft.fuelsearcher.model.FuelTable;
//import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
//import by.aurorasoft.fuelsearcher.service.factory.derivingsearcherfactory.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearcher;
//import by.aurorasoft.fuelsearcher.util.XWPFContentUtil;
//import lombok.RequiredArgsConstructor;
//import org.apache.poi.xwpf.usermodel.IBodyElement;
//import org.junit.Test;
//import org.mockito.MockedStatic;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesIgnoringWhitespacesAndCase;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.mockStatic;
//
//public final class PropertyMetadataSearcherTest {
//
//    @Test
//    public void searcherShouldBeAbleToFindMetadata() {
//        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher();
//
//        final TestSource givenSource = new TestSource();
//
//        final boolean actual = givenSearcher.isAbleToFind(givenSource);
//        assertTrue(actual);
//    }
//
//    @Test
//    public void searcherShouldNotBeAbleToFindMetadata() {
//        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher();
//        final PropertyMetadataSource givenSource = () -> {
//            throw new UnsupportedOperationException();
//        };
//
//        final boolean actual = givenSearcher.isAbleToFind(givenSource);
//        assertFalse(actual);
//    }
//
//    @Test
//    public void metadataShouldBeFoundWithoutRemovingDuplicatedAllowableValues() {
//        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
//        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
//                givenAllowableValues,
//                false
//        );
//
//        final FuelTable givenFuelTable = mock(FuelTable.class);
//
//        final String givenPropertyName = "property-name";
//        final TestSource givenSource = new TestSource(givenPropertyName);
//
//        final PropertyMetadata actual = givenSearcher.find(givenFuelTable, givenSource);
//        final PropertyMetadata expected = PropertyMetadata.builder()
//                .propertyName(givenPropertyName)
//                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void metadataShouldBeFoundWithRemovingDuplicatedAllowableValues() {
//        try (final MockedStatic<XWPFContentUtil> mockedContentUtil = mockStatic(XWPFContentUtil.class)) {
//            final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
//            final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
//                    givenAllowableValues,
//                    true
//            );
//
//            final Stream<String> givenUniqueAllowableValues = Stream.of("first-value", "second-value");
//            mockedContentUtil.when(
//                    () -> removeDuplicatesIgnoringWhitespacesAndCase(any(Stream.class))
//            ).thenReturn(givenUniqueAllowableValues);
//
//            final FuelTable givenFuelTable = mock(FuelTable.class);
//
//            final String givenPropertyName = "property-name";
//            final TestSource givenSource = new TestSource(givenPropertyName);
//
//            final PropertyMetadata actual = givenSearcher.find(givenFuelTable, givenSource);
//            final PropertyMetadata expected = PropertyMetadata.builder()
//                    .propertyName(givenPropertyName)
//                    .allowableValues(new String[]{"first-value", "second-value"})
//                    .build();
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test(expected = ClassCastException.class)
//    public void metadataShouldNotBeFoundBecauseOfNotSuitableType() {
//        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
//        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
//                givenAllowableValues,
//                false
//        );
//
//        final FuelTable givenFuelTable = mock(FuelTable.class);
//        final PropertyMetadataSource givenSource = () -> {
//            throw new UnsupportedOperationException();
//        };
//
//        givenSearcher.find(givenFuelTable, givenSource);
//    }
//
//    @RequiredArgsConstructor
//    private static final class TestSource implements PropertyMetadataSource {
//        private final String propertyName;
//
//        public TestSource() {
//            this(null);
//        }
//
//        @Override
//        public String findPropertyName() {
//            return this.propertyName;
//        }
//
//    }
//
//    private static final class TestPropertyMetadataSearcher extends PropertyMetadataSearcher<TestSource> {
//        private final List<String> allowableValues;
//        private final boolean allowableValuesDuplicated;
//
//        public TestPropertyMetadataSearcher() {
//            this(null, false);
//        }
//
//        public TestPropertyMetadataSearcher(final List<String> allowableValues,
//                                            final boolean allowableValuesDuplicated) {
//            super(TestSource.class);
//            this.allowableValues = allowableValues;
//            this.allowableValuesDuplicated = allowableValuesDuplicated;
//        }
//
//        @Override
//        protected Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final TestSource source) {
//            return this.allowableValues.stream();
//        }
//
//        @Override
//        protected boolean isAllowableValuesDuplicated() {
//            return this.allowableValuesDuplicated;
//        }
//    }
//
//}
