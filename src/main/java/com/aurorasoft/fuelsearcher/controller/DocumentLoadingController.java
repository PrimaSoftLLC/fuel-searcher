package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.documentloading.DocumentLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public final class DocumentLoadingController {
    private static final String HEADER_KEY = "Content-disposition";
    private static final String HEADER_VALUE_TEMPLATE = "attachment; filename=%s.%s";

    private final DocumentLoadingService loadingService;

    @GetMapping("/fuel")
    public ResponseEntity<StreamingResponseBody> loadFuelDocument(final HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=fuel-document.docx");
        return ResponseEntity.ok(
                outputStream -> outputStream.write(
                        this.loadingService.loadFuelDocument()
                )
        );

//        return this.load(DocumentLoadingService::loadFuelDocument);
    }

    @GetMapping("/searcher-config")
    public ResponseEntity<StreamingResponseBody> loadSearcherConfigFile(final HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=searcher-config.xml");
//        return this.load(DocumentLoadingService::loadSearcherConfigFile);
        return ResponseEntity.ok(
                outputStream -> outputStream.write(
                        this.loadingService.loadSearcherConfigFile()
                )
        );
    }
}
