package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class PropertyMetadataSearchingManagerTest {

    @Mock
    private PropertyMetadataSearcher<?> firstMockedSearcher;

    @Mock
    private PropertyMetadataSearcher<?> secondMockedSearcher;

    @Mock
    private PropertyMetadataSearcher<?> thirdMockedSearcher;

    @Test
    public void metadataShouldBeFound() {
        final PropertyMetadataSearchingManager givenSearchingManager = this.createSearchingManager(
                true
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final PropertyMetadataSource givenSource = createMetadataSource();

        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(true);

        final PropertyMetadata givenMetadata = mock(PropertyMetadata.class);
        when(this.secondMockedSearcher.find(same(givenFuelTable), same(givenSource))).thenReturn(givenMetadata);

        final Optional<PropertyMetadata> optionalActual = givenSearchingManager.findIfNecessary(
                givenFuelTable,
                givenSource
        );
        assertTrue(optionalActual.isPresent());
        final PropertyMetadata actual = optionalActual.get();
        assertSame(givenMetadata, actual);

        verify(this.firstMockedSearcher, times(1)).isAbleToFind(same(givenSource));
        verify(this.secondMockedSearcher, times(1)).isAbleToFind(same(givenSource));
        verify(this.thirdMockedSearcher, times(0)).isAbleToFind(any());

        verify(this.firstMockedSearcher, times(0)).find(any(FuelTable.class), any());
        verify(this.secondMockedSearcher, times(1)).find(same(givenFuelTable), same(givenSource));
        verify(this.firstMockedSearcher, times(0)).find(any(FuelTable.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void metadataShouldNotBeFoundBecauseOfNoSuitableSearcher() {
        final PropertyMetadataSearchingManager givenSearchingManager = this.createSearchingManager(
                true
        );

        final FuelTable givenFuelTable = mock(FuelTable.class);
        final PropertyMetadataSource givenSource = createMetadataSource();

        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.thirdMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);

        givenSearchingManager.findIfNecessary(givenFuelTable, givenSource);
    }

    @Test
    public void metadataShouldNotBeFoundBecauseOfSearchingIsNotRequired() {
        final PropertyMetadataSearchingManager givenSearchingManager = this.createSearchingManager(
                false
        );
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final PropertyMetadataSource givenSource = createMetadataSource();

        final Optional<PropertyMetadata> optionalActual = givenSearchingManager.findIfNecessary(
                givenFuelTable,
                givenSource
        );
        assertTrue(optionalActual.isEmpty());
    }

    private PropertyMetadataSearchingManager createSearchingManager(final boolean metadataRefreshingEnabled) {
        return new PropertyMetadataSearchingManager(
                List.of(this.firstMockedSearcher, this.secondMockedSearcher, this.thirdMockedSearcher),
                metadataRefreshingEnabled
        );
    }

    private static PropertyMetadataSource createMetadataSource() {
        return () -> {
            throw new UnsupportedOperationException();
        };
    }
}
