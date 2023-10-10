package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.documentuploading.DocumentUploadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/uploadingDocument")
@RequiredArgsConstructor
public final class DocumentUploadingController {
    private final DocumentUploadingService uploadingService;

    @PutMapping
    public ResponseEntity<?> uploadFuelDocument(@RequestParam(name = "fuelDocument") final MultipartFile fuelDocument,
                                                @RequestParam(name = "searcherConfig") final MultipartFile searcherConfigFile) {
        this.uploadingService.upload(fuelDocument, searcherConfigFile);
        return ok().build();
    }
}
