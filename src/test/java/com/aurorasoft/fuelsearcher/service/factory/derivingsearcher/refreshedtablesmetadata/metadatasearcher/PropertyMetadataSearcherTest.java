package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.util.XWPFContentUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesConsideringOnlyLettersAndDigits;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        final PropertyMetadataSource givenSource = new PropertyMetadataSource(null) {
        };

        final boolean actual = givenSearcher.isAbleToFind(givenSource);
        assertFalse(actual);
    }

    @Test
    public void metadataShouldBeFoundWithoutRemovingDuplicatedAllowableValues() {
        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenAllowableValues,
                false
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);

        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenPropertyExtractor = createPropertyExtractor(givenPropertyName);
        final TestSource givenSource = new TestSource(givenPropertyExtractor);

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
            final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
            final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                    givenAllowableValues,
                    true
            );

            final Stream<String> givenUniqueAllowableValues = Stream.of("first-value", "second-value");
            mockedContentUtil.when(
                    () -> removeDuplicatesConsideringOnlyLettersAndDigits(any(Stream.class))
            ).thenReturn(givenUniqueAllowableValues);

            final FuelTable givenFuelTable = mock(FuelTable.class);

            final String givenPropertyName = "property-name";
            final SpecificationPropertyExtractor givenPropertyExtractor = createPropertyExtractor(givenPropertyName);
            final TestSource givenSource = new TestSource(givenPropertyExtractor);

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
        final List<String> givenAllowableValues = List.of("first-value", "second-value", "third-value");
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenAllowableValues,
                false
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);

        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenPropertyExtractor = createPropertyExtractor(givenPropertyName);
        final PropertyMetadataSource givenSource = new PropertyMetadataSource(givenPropertyExtractor) {
        };

        givenSearcher.find(givenFuelTable, givenSource);
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor propertyExtractor = mock(SpecificationPropertyExtractor.class);
        when(propertyExtractor.getPropertyName()).thenReturn(propertyName);
        return propertyExtractor;
    }

    private static final class TestSource extends PropertyMetadataSource {
        public TestSource() {
            this(null);
        }

        public TestSource(final SpecificationPropertyExtractor propertyExtractor) {
            super(propertyExtractor);
        }

    }

    private static final class TestPropertyMetadataSearcher extends PropertyMetadataSearcher<TestSource> {
        private final List<String> allowableValues;
        private final boolean allowableValuesDuplicated;

        public TestPropertyMetadataSearcher() {
            this(null, false);
        }

        public TestPropertyMetadataSearcher(final List<String> allowableValues,
                                            final boolean allowableValuesDuplicated) {
            super(TestSource.class);
            this.allowableValues = allowableValues;
            this.allowableValuesDuplicated = allowableValuesDuplicated;
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
