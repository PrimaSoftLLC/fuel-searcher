package com.aurorasoft.fuelsearcher.service.metadataloader.refreshed;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.util.OutputStreamUtil;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.createObjectOutputStream;
import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.writeObjects;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class RefreshedTablesMetadataSaverTest {
    private static final String GIVEN_PREPARED_METADATA_FILE_PATH = "tables-metadata.txt";

    private final RefreshedTablesMetadataSaver saver = new RefreshedTablesMetadataSaver(
            GIVEN_PREPARED_METADATA_FILE_PATH
    );

    @Test
    public void metadataShouldBeSaved()
            throws Exception {
        try (final MockedStatic<OutputStreamUtil> mockedOutputStreamUtil = mockStatic(OutputStreamUtil.class)) {
            final List<TableMetadata> givenTablesMetadata = List.of(
                    mock(TableMetadata.class),
                    mock(TableMetadata.class),
                    mock(TableMetadata.class)
            );

            final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
            mockedOutputStreamUtil.when(
                    () -> createObjectOutputStream(same(GIVEN_PREPARED_METADATA_FILE_PATH))
            ).thenReturn(givenOutputStream);

            this.saver.save(givenTablesMetadata);

            mockedOutputStreamUtil.verify(
                    () -> writeObjects(same(givenOutputStream), same(givenTablesMetadata)),
                    times(1)
            );

            verify(givenOutputStream, times(1)).close();
        }
    }

    @Test(expected = Exception.class)
    public void outputStreamShouldNotBeClosedAfterMetadataWereSaved()
            throws Exception {
        try (final MockedStatic<OutputStreamUtil> mockedOutputStreamUtil = mockStatic(OutputStreamUtil.class)) {
            final List<TableMetadata> givenTablesMetadata = List.of(
                    mock(TableMetadata.class),
                    mock(TableMetadata.class),
                    mock(TableMetadata.class)
            );

            final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
            mockedOutputStreamUtil.when(
                    () -> createObjectOutputStream(same(GIVEN_PREPARED_METADATA_FILE_PATH))
            ).thenReturn(givenOutputStream);

            doThrow(IOException.class).when(givenOutputStream).close();

            this.saver.save(givenTablesMetadata);
        }
    }
}
