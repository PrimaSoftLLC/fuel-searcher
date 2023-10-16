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

    private final String fuelDocumentPath;
    private final String searcherConfigFilePath;

    public DownloadingFileService(@Value("${fuel-document.path}") final String fuelDocumentPath,
                                  @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentPath = fuelDocumentPath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public DownloadedFile downloadFuelDocument() {
        return download(
                this.fuelDocumentPath,
                FUEL_DOCUMENT_NAME,
                FUEL_DOCUMENT_CONTENT_TYPE
        );
    }

    public DownloadedFile downloadSearcherConfigFile() {
        return download(
                this.searcherConfigFilePath,
                SEARCHER_CONFIG_FILE_NAME,
                SEARCHER_CONFIG_FILE_CONTENT_TYPE
        );
    }

    private static DownloadedFile download(final String filePath, final String name, final ContentType contentType) {
        final byte[] bytes = readAsBytes(filePath);
        return new DownloadedFile(bytes, name, contentType);
    }
}
