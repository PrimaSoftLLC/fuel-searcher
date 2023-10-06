package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class PropertyMetadataSearchingManagerTest {

    @Mock
    private TablePropertyMetadataSearcher<?> firstMockedSearcher;

    @Mock
    private TablePropertyMetadataSearcher<?> secondMockedSearcher;

    @Mock
    private TablePropertyMetadataSearcher<?> thirdMockedSearcher;

    private TablePropertyMetadataSearchingManager searchingManager;

    @Before
    public void initializeSearchingManager() {
        this.searchingManager = new TablePropertyMetadataSearchingManager(
                List.of(
                        this.firstMockedSearcher,
                        this.secondMockedSearcher,
                        this.thirdMockedSearcher
                )
        );
    }

    @Test
    public void metadataShouldBeFound() {
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final PropertyMetadataSource givenSource = createMetadataSource();

        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(true);

        final PropertyMetadata givenMetadata = mock(PropertyMetadata.class);
        when(this.secondMockedSearcher.find(same(givenFuelTable), same(givenSource))).thenReturn(givenMetadata);

        final PropertyMetadata actual = this.searchingManager.find(givenFuelTable, givenSource);
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
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final PropertyMetadataSource givenSource = createMetadataSource();

        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
        when(this.thirdMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);

        this.searchingManager.find(givenFuelTable, givenSource);
    }


    private static PropertyMetadataSource createMetadataSource() {
        return new PropertyMetadataSource(null) {
        };
    }
}
