package com.aurorasoft.fuelsearcher.service.documentuploading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.aurorasoft.fuelsearcher.util.ApplicationRestartingUtil.restartApplication;
import static com.aurorasoft.fuelsearcher.util.FileUtil.removeFileIfExists;
import static com.aurorasoft.fuelsearcher.util.FileUtil.writeFile;

//TODO: refactor tests
@Service
public final class DocumentUploadingService {
    private final String fuelDocumentPath;
    private final String searcherConfigFilePath;
    private final String preparedMetadataFilePath;

    public DocumentUploadingService(@Value("${fuel-document.path}") final String fuelDocumentPath,
                                    @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath,
                                    @Value("${metadata.file-path}") final String preparedMetadataFilePath) {
        this.fuelDocumentPath = fuelDocumentPath;
        this.searcherConfigFilePath = searcherConfigFilePath;
        this.preparedMetadataFilePath = preparedMetadataFilePath;
    }

    public void upload(final MultipartFile fuelDocument, final MultipartFile searcherConfigFile) {
        writeFile(fuelDocument, this.fuelDocumentPath);
        writeFile(searcherConfigFile, this.searcherConfigFilePath);
        removeFileIfExists(this.preparedMetadataFilePath);
        restartApplication();
    }
}
