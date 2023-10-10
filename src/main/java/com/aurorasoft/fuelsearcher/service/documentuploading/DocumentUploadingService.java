package com.aurorasoft.fuelsearcher.service.documentuploading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.aurorasoft.fuelsearcher.util.ApplicationRestartingUtil.restartApplication;
import static com.aurorasoft.fuelsearcher.util.FileUtil.writeFile;

@Service
public final class DocumentUploadingService {
    private final String fuelDocumentPath;
    private final String searcherConfigFilePath;

    public DocumentUploadingService(@Value("${fuel-document.path}") final String fuelDocumentPath,
                                    @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentPath = fuelDocumentPath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public void upload(final MultipartFile fuelDocument, final MultipartFile searcherConfigFile) {
        writeFile(fuelDocument, this.fuelDocumentPath);
        writeFile(searcherConfigFile, this.searcherConfigFilePath);
        restartApplication();
    }
}
