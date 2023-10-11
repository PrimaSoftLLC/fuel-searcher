package com.aurorasoft.fuelsearcher.service.downloadingfile;

import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.XML;
import static com.aurorasoft.fuelsearcher.util.FileUtil.readAsBytes;

@Service
public final class DownloadingFileService {
    private static final String FUEL_DOCUMENT_NAME = "fuel-document";
    private static final ContentType FUEL_DOCUMENT_CONTENT_TYPE = DOCX;

    private static final String SEARCHER_CONFIG_FILE_NAME = "searcher-config";
    private static final ContentType SEARCHER_CONFIG_FILE_CONTENT_TYPE = XML;

    private final String fuelDocumentFilePath;
    private final String searcherConfigFilePath;

    public DownloadingFileService(@Value("${fuel-document.path}") final String fuelDocumentFilePath,
                                  @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentFilePath = fuelDocumentFilePath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public DownloadedFile downloadFuelDocument() {
        return load(
                this.fuelDocumentFilePath,
                FUEL_DOCUMENT_NAME,
                FUEL_DOCUMENT_CONTENT_TYPE
        );
    }

    public DownloadedFile downloadSearcherConfigFile() {
        return load(
                this.searcherConfigFilePath,
                SEARCHER_CONFIG_FILE_NAME,
                SEARCHER_CONFIG_FILE_CONTENT_TYPE
        );
    }

    private static DownloadedFile load(final String filePath, final String name, final ContentType contentType) {
        final byte[] bytes = readAsBytes(filePath);
        return new DownloadedFile(bytes, name, contentType);
    }
}
