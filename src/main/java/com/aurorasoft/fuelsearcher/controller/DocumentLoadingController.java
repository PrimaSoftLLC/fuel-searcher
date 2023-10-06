package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.documentloading.DocumentLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentLoadingController {
    private static final String HEADER_VALUE_TEMPLATE = "attachment; filename=\"%s\"";

    private final DocumentLoadingService loadingService;

    @GetMapping("/fuel")
    public ResponseEntity<Resource> loadFuelDocument() {
        return this.loadResource(DocumentLoadingService::loadFuelDocument);
    }

    @GetMapping("/searcher-config")
    public ResponseEntity<Resource> loadSearcherConfigFile() {
        return this.loadResource(DocumentLoadingService::loadSearcherConfigFile);
    }

    private ResponseEntity<Resource> loadResource(final Function<DocumentLoadingService, Resource> loadingFunction) {
        final Resource resource = loadingFunction.apply(this.loadingService);
        final String headerValue = findHeaderValue(resource);
        return ok().contentType(APPLICATION_OCTET_STREAM)
                .header(CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    private static String findHeaderValue(final Resource resource) {
        final String fileName = resource.getFilename();
        return HEADER_VALUE_TEMPLATE.formatted(fileName);
    }
}
