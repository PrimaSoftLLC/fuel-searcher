package com.aurorasoft.fuelsearcher.service.documentloading;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static java.nio.file.Files.readAllBytes;
import static org.junit.Assert.assertEquals;

public final class DocumentLoadingServiceTest extends AbstractContextTest {
    private static final String FUEL_DOCUMENT_FILE_PATH = "./src/test/resources/fuel-document.docx";
    private static final String FUEL_SEARCHER_CONFIG_FILE_PATH = "./src/test/resources/fuel-searchers.xml";

    private static final String FIELD_NAME_FUEL_DOCUMENT_FILE_PATH = "fuelDocumentFilePath";
    private static final String FIELD_NAME_SEARCHER_CONFIG_FILE_PATH = "searcherConfigFilePath";

    @Autowired
    private DownloadingFileService loadingService;

    @Test
    public void filePathsShouldBeInjected() {
        final String actualFuelDocumentFilePath = this.findFuelDocumentFilePath();
        final String expectedFuelDocumentFilePath = "./src/test/resources/fuel-document.docx";
        assertEquals(expectedFuelDocumentFilePath, actualFuelDocumentFilePath);

        final String actualSearcherConfigFilePath = this.findSearcherConfigFilePath();
        final String expectedSearcherConfigFilePath = "./src/test/resources/fuel-searchers.xml";
        assertEquals(expectedSearcherConfigFilePath, actualSearcherConfigFilePath);
    }

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


    private String findFuelDocumentFilePath() {
        return findProperty(
                this.loadingService,
                FIELD_NAME_FUEL_DOCUMENT_FILE_PATH,
                String.class
        );
    }

    private String findSearcherConfigFilePath() {
        return findProperty(
                this.loadingService,
                FIELD_NAME_SEARCHER_CONFIG_FILE_PATH,
                String.class
        );
    }

    private static byte[] readFuelDocument()
            throws Exception {
        return readFile(FUEL_DOCUMENT_FILE_PATH);
    }

    private static byte[] readFuelSearcherConfigFile()
            throws Exception {
        return readFile(FUEL_SEARCHER_CONFIG_FILE_PATH);
    }

    private static byte[] readFile(final String filePath)
            throws Exception {
        final Path path = Paths.get(filePath);
        return readAllBytes(path);
    }
}
