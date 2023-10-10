package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.documentuploading.DocumentUploadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploading-document")
@RequiredArgsConstructor
public final class DocumentUploadingController {
    private final DocumentUploadingService uploadingService;

    @PostMapping
    public void uploadFuelDocument(@RequestParam("fuel-document") final MultipartFile fuelDocument,
                                   @RequestParam("searcher-config-file") final MultipartFile searcherConfigFile) {
        this.uploadingService.upload(fuelDocument, searcherConfigFile);
    }
}
