package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aurorasoft.fuelsearcher.util.FileUtil.writeFile;
import static java.nio.file.Files.write;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class FileUtilTest {

    @Test
    public void fileShouldBeWritten()
            throws Exception {
        try (final MockedStatic<Paths> mockedStaticPaths = mockStatic(Paths.class);
             final MockedStatic<Files> mockedStaticFiles = mockStatic(Files.class)) {
            final byte[] givenFileBytes = {1, 2, 3, 4, 5};
            final MultipartFile givenFile = mock(MultipartFile.class);
            when(givenFile.getBytes()).thenReturn(givenFileBytes);

            final String givenFilePath = "file.txt";

            final Path givenPath = mock(Path.class);
            mockedStaticPaths.when(() -> Paths.get(givenFilePath)).thenReturn(givenPath);

            writeFile(givenFile, givenFilePath);

            mockedStaticFiles.verify(
                    () -> write(same(givenPath), same(givenFileBytes)),
                    times(1)
            );
        }
    }

    @Test(expected = Exception.class)
    public void fileShouldNotBeWrittenBecauseOfGettingFileBytesIsFailed()
            throws Exception {
        try (final MockedStatic<Paths> mockedStaticPaths = mockStatic(Paths.class)) {
            final MultipartFile givenFile = mock(MultipartFile.class);
            doThrow(IOException.class).when(givenFile).getBytes();

            final String givenFilePath = "file.txt";

            final Path givenPath = mock(Path.class);
            mockedStaticPaths.when(() -> Paths.get(givenFilePath)).thenReturn(givenPath);

            writeFile(givenFile, givenFilePath);
        }
    }

    @Test(expected = Exception.class)
    public void fileShouldNotBeWrittenBecauseOfWritingFileBytesIsFailed()
            throws Exception {
        try (final MockedStatic<Paths> mockedStaticPaths = mockStatic(Paths.class);
             final MockedStatic<Files> mockedStaticFiles = mockStatic(Files.class)) {
            final byte[] givenFileBytes = {1, 2, 3, 4, 5};
            final MultipartFile givenFile = mock(MultipartFile.class);
            when(givenFile.getBytes()).thenReturn(givenFileBytes);

            final String givenFilePath = "file.txt";

            final Path givenPath = mock(Path.class);
            mockedStaticPaths.when(() -> Paths.get(givenFilePath)).thenReturn(givenPath);

            mockedStaticFiles.when(() -> write(same(givenPath), same(givenFileBytes))).thenThrow(IOException.class);

            writeFile(givenFile, givenFilePath);
        }
    }
}
