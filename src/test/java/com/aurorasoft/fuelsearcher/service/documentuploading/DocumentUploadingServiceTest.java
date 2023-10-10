package com.aurorasoft.fuelsearcher.service.documentuploading;

import com.aurorasoft.fuelsearcher.util.ApplicationRestartingUtil;
import com.aurorasoft.fuelsearcher.util.FileUtil;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static com.aurorasoft.fuelsearcher.util.FileUtil.writeFile;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public final class DocumentUploadingServiceTest {
    private static final String GIVEN_FUEL_DOCUMENT_PATH = "fuel-document.docx";
    private static final String GIVEN_SEARCHER_CONFIG_FILE_PATH = "searcher-config.xml";

    private final DocumentUploadingService uploadingService = new DocumentUploadingService(
            GIVEN_FUEL_DOCUMENT_PATH,
            GIVEN_SEARCHER_CONFIG_FILE_PATH
    );

    @Test
    public void documentShouldBeUploaded() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class);
             final MockedStatic<ApplicationRestartingUtil> mockedRestartingUtil = mockStatic(ApplicationRestartingUtil.class)) {
            final MultipartFile givenFuelDocument = new MockMultipartFile("fuel-document", new byte[]{1, 2, 3});
            final MultipartFile givenSearcherConfigFilePath = new MockMultipartFile(
                    "fuel-document",
                    new byte[]{4, 5, 6}
            );

            this.uploadingService.upload(givenFuelDocument, givenSearcherConfigFilePath);

            mockedFileUtil.verify(
                    () -> writeFile(same(givenFuelDocument), same(GIVEN_FUEL_DOCUMENT_PATH)),
                    times(1)
            );
            mockedFileUtil.verify(
                    () -> writeFile(same(givenSearcherConfigFilePath), same(GIVEN_SEARCHER_CONFIG_FILE_PATH)),
                    times(1)
            );
            mockedRestartingUtil.verify(
                    ApplicationRestartingUtil::restartApplication,
                    times(1)
            );
        }
    }
}
