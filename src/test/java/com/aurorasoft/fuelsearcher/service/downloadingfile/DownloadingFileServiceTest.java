package com.aurorasoft.fuelsearcher.service.downloadingfile;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.XML;
import static java.nio.file.Files.readAllBytes;
import static org.junit.Assert.assertEquals;

public final class DownloadingFileServiceTest extends AbstractContextTest {

    @Value("${fuel-document.path}")
    private String fuelDocumentFilePath;

    @Value("${fuel-searcher-config.path}")
    private String searcherConfigFilePath;

    @Autowired
    private DownloadingFileService loadingService;

    @Test
    public void fuelDocumentShouldBeLoaded()
            throws Exception {
        final DownloadedFile actual = this.loadingService.downloadFuelDocument();
        final DownloadedFile expected = DownloadedFile.builder()
                .name("fuel-document")
                .bytes(readFuelDocument())
                .contentType(DOCX)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void searcherConfigFileShouldBeLoaded()
            throws Exception {
        final DownloadedFile actual = this.loadingService.downloadSearcherConfigFile();
        final DownloadedFile expected = DownloadedFile.builder()
                .name("searcher-config")
                .bytes(readFuelSearcherConfigFile())
                .contentType(XML)
                .build();
        assertEquals(expected, actual);
    }

    private byte[] readFuelDocument()
            throws Exception {
        return readFile(this.fuelDocumentFilePath);
    }

    private byte[] readFuelSearcherConfigFile()
            throws Exception {
        return readFile(this.searcherConfigFilePath);
    }

    private static byte[] readFile(final String filePath)
            throws Exception {
        final Path path = Paths.get(filePath);
        return readAllBytes(path);
    }
}
