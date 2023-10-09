package com.aurorasoft.fuelsearcher.service.documentloading;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.assertEquals;

public final class DocumentLoadingServiceTest extends AbstractContextTest {
    private static final String FIELD_NAME_FUEL_DOCUMENT_FILE_PATH = "fuelDocumentFilePath";
    private static final String FIELD_NAME_SEARCHER_CONFIG_FILE_PATH = "searcherConfigFilePath";

    @Autowired
    private DocumentLoadingService loadingService;

    @Test
    public void filePathsShouldBeInjected() {
        final String actualFuelDocumentFilePath = this.findFuelDocumentFilePath();
        final String expectedFuelDocumentFilePath = "./src/test/resources/fuel-document.docx";
        assertEquals(expectedFuelDocumentFilePath, actualFuelDocumentFilePath);

        final String actualSearcherConfigFilePath = this.findSearcherConfigFilePath();
        final String expectedSearcherConfigFilePath = "./src/test/resources/fuel-searchers.xml";
        assertEquals(expectedSearcherConfigFilePath, actualSearcherConfigFilePath);
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
}
