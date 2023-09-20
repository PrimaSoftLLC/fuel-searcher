package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

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
    public void metadataShouldBeFound() {
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = {"first-value", "second-value", "third-value"};
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenPropertyName,
                givenAllowableValues
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final TestSource givenSource = new TestSource();

        final PropertyMetadata actual = givenSearcher.find(givenFuelTable, givenSource);
        final PropertyMetadata expected = PropertyMetadata.builder()
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .build();
        assertEquals(expected, actual);
    }

    @Test(expected = ClassCastException.class)
    public void metadataShouldNotBeFoundBecauseOfNotSuitableType() {
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = {"first-value", "second-value", "third-value"};
        final TestPropertyMetadataSearcher givenSearcher = new TestPropertyMetadataSearcher(
                givenPropertyName,
                givenAllowableValues
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final Object givenSource = new Object();

        givenSearcher.find(givenFuelTable, givenSource);
    }

    private static final class TestSource {

    }

    private static final class TestPropertyMetadataSearcher extends PropertyMetadataSearcher<TestSource> {
        private final String propertyName;
        private final String[] allowableValues;

        public TestPropertyMetadataSearcher() {
            this(null, null);
        }

        public TestPropertyMetadataSearcher(final String propertyName, final String[] allowableValues) {
            super(TestSource.class);
            this.propertyName = propertyName;
            this.allowableValues = allowableValues;
        }

        @Override
        protected String findPropertyName(final TestSource source) {
            return this.propertyName;
        }

        @Override
        protected String[] findAllowableValues(final List<IBodyElement> tableElements, final TestSource source) {
            return this.allowableValues;
        }
    }

}
