//package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata;
//
//import com.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
//import com.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
//import com.aurorasoft.fuelsearcher.model.FuelTable;
//import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
//import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearchingManager;
//import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.List;
//
//import static java.util.Arrays.stream;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public final class RefreshedTablesMetadataFactoryTest {
//
//    @Mock
//    private PropertyMetadataSearchingManager mockedMetadataSearchingManager;
//
//    private RefreshedTablesMetadataFactory metadataFactory;
//
//    @Before
//    public void initializeMetadataFactory() {
//        this.metadataFactory = new RefreshedTablesMetadataFactory(null, this.mockedMetadataSearchingManager);
//    }
//
//    @Test
//    public void tableMetadataShouldBeCreated() {
//        final String givenTableName = "table-name";
//        final FuelTable givenTable = mock(FuelTable.class);
//        final PropertyMetadataSource firstGivenPropertyMetadataSource = mock(PropertyMetadataSource.class);
//        final PropertyMetadataSource secondGivenPropertyMetadataSource = mock(PropertyMetadataSource.class);
//        final PropertyMetadataSource thirdGivenPropertyMetadataSource = mock(PropertyMetadataSource.class);
//        final FuelSearcher givenSearcher = createSearcher(
//                givenTableName,
//                givenTable,
//                firstGivenPropertyMetadataSource,
//                secondGivenPropertyMetadataSource,
//                thirdGivenPropertyMetadataSource
//        );
//
//        final PropertyMetadata firstGivenPropertyMetadata = createPropertyMetadata(255L);
//        when(this.mockedMetadataSearchingManager.find(same(givenTable), same(firstGivenPropertyMetadataSource)))
//                .thenReturn(firstGivenPropertyMetadata);
//
//        final PropertyMetadata secondGivenPropertyMetadata = createPropertyMetadata(256L);
//        when(this.mockedMetadataSearchingManager.find(same(givenTable), same(secondGivenPropertyMetadataSource)))
//                .thenReturn(secondGivenPropertyMetadata);
//
//        final PropertyMetadata thirdGivenPropertyMetadata = createPropertyMetadata(257L);
//        when(this.mockedMetadataSearchingManager.find(same(givenTable), same(thirdGivenPropertyMetadataSource)))
//                .thenReturn(thirdGivenPropertyMetadata);
//
//        final TableMetadata actual = this.metadataFactory.createDerivedObject(givenSearcher);
//        final TableMetadata expected = TableMetadata.builder()
//                .tableName(givenTableName)
//                .propertiesMetadata(
//                        List.of(
//                                firstGivenPropertyMetadata,
//                                secondGivenPropertyMetadata,
//                                thirdGivenPropertyMetadata
//                        )
//                )
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static FuelSearcher createSearcher(final String tableName,
//                                               final FuelTable table,
//                                               final PropertyMetadataSource... propertyMetadataSources) {
//        final FuelSearcher searcher = mock(FuelSearcher.class);
//        when(searcher.findTableName()).thenReturn(tableName);
//        when(searcher.getTable()).thenReturn(table);
//        when(searcher.findUsedPropertyMetadataSources()).thenReturn(stream(propertyMetadataSources));
//        return searcher;
//    }
//
//    private static PropertyMetadata createPropertyMetadata(final Long id) {
//        return PropertyMetadata.builder()
//                .id(id)
//                .build();
//    }
//}
