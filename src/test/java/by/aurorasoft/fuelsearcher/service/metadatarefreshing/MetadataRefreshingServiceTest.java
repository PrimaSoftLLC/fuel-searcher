package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class MetadataRefreshingServiceTest {

    @Mock
    private TableMetadataService mockedTableMetadataService;

    @Mock
    private PropertyMetadataService mockedPropertyMetadataService;

    @Mock
    private SearchersParsingResult mockedSearchersParsingResult;

    private MetadataRefreshingService metadataRefreshingService;

    @Before
    public void initializeMetadataRefreshingService() {
        this.metadataRefreshingService = new MetadataRefreshingService(
                this.mockedTableMetadataService,
                this.mockedPropertyMetadataService,
                this.mockedSearchersParsingResult
        );
    }

    @Test
    public void metadataShouldBeRefreshed() {
        final List<TableMetadata> givenTablesMetadata = List.of(
                mock(TableMetadata.class),
                mock(TableMetadata.class)
        );
        when(this.mockedSearchersParsingResult.takeAwayTablesMetadata()).thenReturn(Optional.of(givenTablesMetadata));

        final Long firstGivenTableMetadataId = 255L;
        final PropertyMetadata firstGivenPropertyMetadata = createNotSavedPropertyMetadata();
        final PropertyMetadata secondGivenPropertyMetadata = createNotSavedPropertyMetadata();
        final TableMetadata firstGivenSavedTableMetadata = createTableMetadata(
                firstGivenTableMetadataId,
                firstGivenPropertyMetadata,
                secondGivenPropertyMetadata
        );

        final Long secondGivenTableMetadataId = 256L;
        final PropertyMetadata thirdGivenPropertyMetadata = createNotSavedPropertyMetadata();
        final PropertyMetadata fourthGivenPropertyMetadata = createNotSavedPropertyMetadata();
        final TableMetadata secondGivenSavedTableMetadata = createTableMetadata(
                secondGivenTableMetadataId,
                thirdGivenPropertyMetadata,
                fourthGivenPropertyMetadata
        );

        final List<TableMetadata> givenSavedTablesMetadata = List.of(
                firstGivenSavedTableMetadata,
                secondGivenSavedTableMetadata
        );
        when(this.mockedTableMetadataService.saveAll(same(givenTablesMetadata))).thenReturn(givenSavedTablesMetadata);

        this.metadataRefreshingService.refresh();

        final List<PropertyMetadata> expectedNewPropertiesMetadata = List.of(
                PropertyMetadata.builder()
                        .tableMetadataId(firstGivenTableMetadataId)
                        .build(),
                PropertyMetadata.builder()
                        .tableMetadataId(firstGivenTableMetadataId)
                        .build(),
                PropertyMetadata.builder()
                        .tableMetadataId(secondGivenTableMetadataId)
                        .build(),
                PropertyMetadata.builder()
                        .tableMetadataId(secondGivenTableMetadataId)
                        .build()
        );
        verify(this.mockedPropertyMetadataService, times(1))
                .saveAll(eq(expectedNewPropertiesMetadata));
    }

    @Test(expected = Exception.class)
    public void metadataShouldNotBeRefreshedBecauseOfTablesMetadataIsNull() {
        when(this.mockedSearchersParsingResult.takeAwayTablesMetadata()).thenReturn(empty());

        this.metadataRefreshingService.refresh();
    }

    @Test(expected = Exception.class)
    public void metadataShouldNotBeRefreshedBecauseOfTablesMetadataIsEmpty() {
        when(this.mockedSearchersParsingResult.takeAwayTablesMetadata()).thenReturn(Optional.of(emptyList()));

        this.metadataRefreshingService.refresh();
    }

    private static TableMetadata createTableMetadata(final Long id, final PropertyMetadata... propertiesMetadata) {
        final List<PropertyMetadata> propertiesMetadataAsList = asList(propertiesMetadata);
        final TableMetadata tableMetadata = mock(TableMetadata.class);
        when(tableMetadata.getId()).thenReturn(id);
        when(tableMetadata.getPropertiesMetadata()).thenReturn(propertiesMetadataAsList);
        return tableMetadata;
    }

    private static PropertyMetadata createNotSavedPropertyMetadata() {
        final PropertyMetadata metadata = mock(PropertyMetadata.class);
        when(metadata.getId()).thenReturn(null);
        return metadata;
    }
}
