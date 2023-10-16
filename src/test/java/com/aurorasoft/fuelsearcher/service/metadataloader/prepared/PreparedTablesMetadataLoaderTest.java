package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.util.FileUtil;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.util.List;

import static com.aurorasoft.fuelsearcher.util.FileUtil.isFileExist;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class PreparedTablesMetadataLoaderTest {
    private static final String GIVEN_PREPARED_METADATA_FILE_PATH = "tables-metadata.txt";

    private final PreparedTablesMetadataLoader loader = new PreparedTablesMetadataLoader(
            GIVEN_PREPARED_METADATA_FILE_PATH
    );

    @Test
    public void loaderShouldBeAbleToLoad() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            mockedFileUtil.when(() -> isFileExist(same(GIVEN_PREPARED_METADATA_FILE_PATH))).thenReturn(true);

            final boolean actual = this.loader.isAbleToLoad();
            assertTrue(actual);
        }
    }

    @Test
    public void loaderShouldNotBeAbleToLoad() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            mockedFileUtil.when(() -> isFileExist(same(GIVEN_PREPARED_METADATA_FILE_PATH))).thenReturn(false);

            final boolean actual = this.loader.isAbleToLoad();
            assertFalse(actual);
        }
    }

    @Test
    public void metadataShouldBeLoaded() {
        final List<TableMetadata> givenTablesMetadata = List.of(
                mock(TableMetadata.class),
                mock(TableMetadata.class),
                mock(TableMetadata.class)
        );
        try (final MockedConstruction<TableMetadataDeserializer> mockedConstruction
                     = mockConstruction(
                TableMetadataDeserializer.class,
                (mockedDeserializer, context) -> when(mockedDeserializer.deserialize()).thenReturn(givenTablesMetadata))
        ) {
            final List<TableMetadata> actual = this.loader.load();
            assertSame(givenTablesMetadata, actual);

            final List<TableMetadataDeserializer> constructedDeserializers = mockedConstruction.constructed();
            assertEquals(1, constructedDeserializers.size());

            final TableMetadataDeserializer constructedDeserializer = constructedDeserializers.get(0);
            verify(constructedDeserializer, times(1)).close();
        }
    }
}
