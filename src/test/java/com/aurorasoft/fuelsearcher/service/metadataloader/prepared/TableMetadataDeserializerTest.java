package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.util.InputStreamUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.*;
import static java.util.Optional.empty;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class TableMetadataDeserializerTest {
    private static final String FIELD_NAME_INPUT_STREAM = "inputStream";

    private static final String EMPTY_STRING = "";

    @Test
    public void deserializerShouldBeCreated() {
        final String givenFilePath = "file.txt";
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        final TableMetadataDeserializer actual = createDeserializer(givenFilePath, givenInputStream);

        final ObjectInputStream actualInputStream = findInputStream(actual);
        assertSame(givenInputStream, actualInputStream);
    }

    @Test
    public void tablesMetadataShouldBeDeserialized() {
        try (final MockedStatic<InputStreamUtil> mockedInputStreamUtil = mockStatic(InputStreamUtil.class)) {
            final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);
            final TableMetadataDeserializer givenDeserializer = createDeserializer(
                    givenInputStream,
                    mockedInputStreamUtil
            );

            final TableMetadata firstGivenTableMetadata = createTableMetadata("first-table");
            final TableMetadata secondGivenTableMetadata = createTableMetadata("second-table");
            mockedInputStreamUtil.when(() -> readObjectIfExist(same(givenInputStream), same(TableMetadata.class)))
                    .thenReturn(Optional.of(firstGivenTableMetadata))
                    .thenReturn(Optional.of(secondGivenTableMetadata))
                    .thenReturn(empty());

            final List<TableMetadata> actual = givenDeserializer.deserialize();
            final List<TableMetadata> expected = List.of(firstGivenTableMetadata, secondGivenTableMetadata);
            assertEquals(expected, actual);

            mockedInputStreamUtil.verify(
                    () -> readObjectIfExist(same(givenInputStream), same(TableMetadata.class)),
                    times(3)
            );
        }
    }

    @Test
    public void tablesMetadataShouldNotBeDeserialized() {
        try (final MockedStatic<InputStreamUtil> mockedInputStreamUtil = mockStatic(InputStreamUtil.class)) {
            final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);
            final TableMetadataDeserializer givenDeserializer = createDeserializer(
                    givenInputStream,
                    mockedInputStreamUtil
            );

            mockedInputStreamUtil.when(
                    () -> readObjectIfExist(same(givenInputStream), same(TableMetadata.class))
            ).thenReturn(empty());

            final List<TableMetadata> actual = givenDeserializer.deserialize();
            assertTrue(actual.isEmpty());

            mockedInputStreamUtil.verify(
                    () -> readObjectIfExist(same(givenInputStream), same(TableMetadata.class)),
                    times(1)
            );
        }
    }

    @Test
    public void deserializerShouldBeClosed() {
        try (final MockedStatic<InputStreamUtil> mockedInputStreamUtil = mockStatic(InputStreamUtil.class)) {
            final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);
            final TableMetadataDeserializer givenDeserializer = createDeserializer(
                    givenInputStream,
                    mockedInputStreamUtil
            );

            givenDeserializer.close();

            mockedInputStreamUtil.verify(() -> closeStream(same(givenInputStream)));
        }
    }

    private static TableMetadataDeserializer createDeserializer(final ObjectInputStream inputStream,
                                                                final MockedStatic<InputStreamUtil> mockedInputStreamUtil) {
        return createDeserializer(
                EMPTY_STRING,
                inputStream,
                mockedInputStreamUtil
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static TableMetadataDeserializer createDeserializer(final String givenFilePath,
                                                                final ObjectInputStream inputStream) {
        try (final MockedStatic<InputStreamUtil> mockedInputStreamUtil = mockStatic(InputStreamUtil.class)) {
            return createDeserializer(
                    givenFilePath,
                    inputStream,
                    mockedInputStreamUtil
            );
        }
    }

    private static TableMetadataDeserializer createDeserializer(final String givenFilePath,
                                                                final ObjectInputStream inputStream,
                                                                final MockedStatic<InputStreamUtil> mockedInputStreamUtil) {
        mockedInputStreamUtil.when(() -> createObjectInputStream(same(givenFilePath))).thenReturn(inputStream);
        return new TableMetadataDeserializer(givenFilePath);
    }

    private static ObjectInputStream findInputStream(final TableMetadataDeserializer deserializer) {
        return findProperty(
                deserializer,
                FIELD_NAME_INPUT_STREAM,
                ObjectInputStream.class
        );
    }

    private static TableMetadata createTableMetadata(final String tableName) {
        return TableMetadata.builder()
                .tableName(tableName)
                .build();
    }
}
