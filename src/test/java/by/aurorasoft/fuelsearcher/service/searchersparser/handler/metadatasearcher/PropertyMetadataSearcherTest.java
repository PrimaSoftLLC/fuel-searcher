package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearcher;
import by.aurorasoft.fuelsearcher.util.XWPFContentUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesIgnoringWhitespacesAndCase;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public final class PropertyMetadataSearcherTest {

    @Test
    public void searcherShouldBeAbleToFindMetadata() {
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher();
        final TestSource givenSource = new TestSource();

        final boolean actual = givenSearcher.isAbleToFind(givenSource);
        assertTrue(actual);
    }

    @Test
    public void searcherShouldNotBeAbleToFindMetadata() {
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher();
        final Object givenSource = new Object();

        final boolean actual = givenSearcher.isAbleToFind(givenSource);
        assertFalse(actual);
    }

    @Test
    public void metadataShouldBeFoundWithoutRemovingDuplicatedAllowableValues() {
        final String givenPropertyName = "property-name";
        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenPropertyName,
                givenAllowableValues,
                false
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final TestSource givenSource = new TestSource();

        final PropertyMetadata actual = givenSearcher.find(givenFuelTable, givenSource);
        final PropertyMetadata expected = PropertyMetadata.builder()
                .propertyName(givenPropertyName)
                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
                .build();
        assertEquals(expected, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void metadataShouldBeFoundWithRemovingDuplicatedAllowableValues() {
        try (final MockedStatic<XWPFContentUtil> mockedContentUtil = mockStatic(XWPFContentUtil.class)) {
            final String givenPropertyName = "property-name";
            final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
            final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                    givenPropertyName,
                    givenAllowableValues,
                    true
            );

            final Stream<String> givenUniqueAllowableValues = Stream.of("first-value", "second-value");
            mockedContentUtil.when(
                    () -> removeDuplicatesIgnoringWhitespacesAndCase(any(Stream.class))
            ).thenReturn(givenUniqueAllowableValues);

            final FuelTable givenFuelTable = mock(FuelTable.class);
            final TestSource givenSource = new TestSource();

            final PropertyMetadata actual = givenSearcher.find(givenFuelTable, givenSource);
            final PropertyMetadata expected = PropertyMetadata.builder()
                    .propertyName(givenPropertyName)
                    .allowableValues(new String[]{"first-value", "second-value"})
                    .build();
            assertEquals(expected, actual);
        }
    }

    @Test(expected = ClassCastException.class)
    public void metadataShouldNotBeFoundBecauseOfNotSuitableType() {
        final String givenPropertyName = "property-name";
        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenPropertyName,
                givenAllowableValues,
                false
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final Object givenSource = new Object();

        givenSearcher.find(givenFuelTable, givenSource);
    }

    private static final class TestSource {

    }

    private static final class TestPropertyMetadataSearcher extends PropertyMetadataSearcher<TestSource> {
        private final String propertyName;
        private final List<String> allowableValues;
        private final boolean allowableValuesDuplicated;

        public TestPropertyMetadataSearcher() {
            this(null, null, false);
        }

        public TestPropertyMetadataSearcher(final String propertyName,
                                            final List<String> allowableValues,
                                            final boolean allowableValuesDuplicated) {
            super(TestSource.class);
            this.propertyName = propertyName;
            this.allowableValues = allowableValues;
            this.allowableValuesDuplicated = allowableValuesDuplicated;
        }

        @Override
        protected String findPropertyName(final TestSource source) {
            return this.propertyName;
        }

        @Override
        protected Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final TestSource source) {
            return this.allowableValues.stream();
        }

        @Override
        protected boolean isAllowableValuesDuplicated() {
            return this.allowableValuesDuplicated;
        }
    }

}
