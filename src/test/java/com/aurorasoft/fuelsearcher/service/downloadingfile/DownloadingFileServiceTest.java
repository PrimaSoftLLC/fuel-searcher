package com.aurorasoft.fuelsearcher.service.downloadingfile;

import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import com.aurorasoft.fuelsearcher.util.FileUtil;
import org.junit.Test;
import org.mockito.MockedStatic;

import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.XML;
import static com.aurorasoft.fuelsearcher.util.FileUtil.readAsBytes;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mockStatic;

public final class DownloadingFileServiceTest {
    private static final String GIVEN_FUEL_DOCUMENT_PATH = "fuel-document.docx";
    private static final String GIVEN_SEARCHER_CONFIG_FILE_PATH = "searcher-config.xml";

    private final DownloadingFileService loadingService = new DownloadingFileService(
            GIVEN_FUEL_DOCUMENT_PATH,
            GIVEN_SEARCHER_CONFIG_FILE_PATH
    );

    @Test
    public void fuelDocumentShouldBeLoaded() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            final byte[] givenBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            mockedFileUtil.when(() -> readAsBytes(same(GIVEN_FUEL_DOCUMENT_PATH))).thenReturn(givenBytes);

            final DownloadedFile actual = this.loadingService.downloadFuelDocument();
            final DownloadedFile expected = DownloadedFile.builder()
                    .name("fuel-document")
                    .bytes(givenBytes)
                    .contentType(DOCX)
                    .build();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void searcherConfigFileShouldBeLoaded() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            final byte[] givenBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            mockedFileUtil.when(() -> readAsBytes(same(GIVEN_SEARCHER_CONFIG_FILE_PATH))).thenReturn(givenBytes);

            final DownloadedFile actual = this.loadingService.downloadSearcherConfigFile();
            final DownloadedFile expected = DownloadedFile.builder()
                    .name("searcher-config")
                    .bytes(givenBytes)
                    .contentType(XML)
                    .build();
            assertEquals(expected, actual);
        }
    }
}
