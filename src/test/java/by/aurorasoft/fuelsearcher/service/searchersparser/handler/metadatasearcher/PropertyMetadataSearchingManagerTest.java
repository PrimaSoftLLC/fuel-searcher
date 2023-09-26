//package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;
//
//import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
//import by.aurorasoft.fuelsearcher.model.FuelTable;
//import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearcher;
//import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearchingManager;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.assertSame;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public final class PropertyMetadataSearchingManagerTest {
//
//    @Mock
//    private PropertyMetadataSearcher<?> firstMockedSearcher;
//
//    @Mock
//    private PropertyMetadataSearcher<?> secondMockedSearcher;
//
//    @Mock
//    private PropertyMetadataSearcher<?> thirdMockedSearcher;
//
//    private PropertyMetadataSearchingManager searchingManager;
//
//    @Before
//    public void initializeSearchingManager() {
//        final List<PropertyMetadataSearcher<?>> searchers = List.of(
//                this.firstMockedSearcher,
//                this.secondMockedSearcher,
//                this.thirdMockedSearcher
//        );
//        this.searchingManager = new PropertyMetadataSearchingManager(searchers);
//    }
//
//    @Test
//    public void metadataShouldBeFound() {
//        final FuelTable givenFuelTable = mock(FuelTable.class);
//        final Object givenSource = new Object();
//
//        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
//        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(true);
//
//        final PropertyMetadata givenMetadata = mock(PropertyMetadata.class);
//        when(this.secondMockedSearcher.find(same(givenFuelTable), same(givenSource))).thenReturn(givenMetadata);
//
//        final PropertyMetadata actual = this.searchingManager.find(givenFuelTable, givenSource);
//        assertSame(givenMetadata, actual);
//
//        verify(this.firstMockedSearcher, times(1)).isAbleToFind(same(givenSource));
//        verify(this.secondMockedSearcher, times(1)).isAbleToFind(same(givenSource));
//        verify(this.thirdMockedSearcher, times(0)).isAbleToFind(any());
//
//        verify(this.firstMockedSearcher, times(0)).find(any(FuelTable.class), any());
//        verify(this.secondMockedSearcher, times(1)).find(same(givenFuelTable), same(givenSource));
//        verify(this.firstMockedSearcher, times(0)).find(any(FuelTable.class), any());
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void metadataShouldNotBeFoundBecauseOfNoSuitableSearcher() {
//        final FuelTable givenFuelTable = mock(FuelTable.class);
//        final Object givenSource = new Object();
//
//        when(this.firstMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
//        when(this.secondMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
//        when(this.thirdMockedSearcher.isAbleToFind(same(givenSource))).thenReturn(false);
//
//        this.searchingManager.find(givenFuelTable, givenSource);
//    }
//}
