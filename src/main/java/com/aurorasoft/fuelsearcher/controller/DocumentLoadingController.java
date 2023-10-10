package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType;
import com.aurorasoft.fuelsearcher.service.documentloading.DocumentLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public final class DocumentLoadingController {
    private static final String HEADER_KEY = "Content-disposition";
    private static final String HEADER_VALUE_TEMPLATE = "attachment; filename=%s.%s";

    private final DocumentLoadingService loadingService;

    @GetMapping("/fuel")
    public ResponseEntity<StreamingResponseBody> loadFuelDocument(final HttpServletResponse response) {
        return this.loadDocument(response, DocumentLoadingService::loadFuelDocument);
    }

    @GetMapping("/searcher-config")
    public ResponseEntity<StreamingResponseBody> loadSearcherConfigFile(final HttpServletResponse response) {
        return this.loadDocument(response, DocumentLoadingService::loadSearcherConfigFile);
    }

    private ResponseEntity<StreamingResponseBody> loadDocument(
            final HttpServletResponse response,
            final Function<DocumentLoadingService, DownloadedFile> loadingFunction
    ) {
        final DownloadedFile loadedDocument = loadingFunction.apply(this.loadingService);
        setHeader(response, loadedDocument);
        return ok(outputStream -> outputStream.write(loadedDocument.getBytes()));
    }

    private static void setHeader(final HttpServletResponse response, final DownloadedFile loadedDocument) {
        final String headerValue = findHeaderValue(loadedDocument);
        response.setHeader(HEADER_KEY, headerValue);
    }

    private static String findHeaderValue(final DownloadedFile loadedDocument) {
        final String documentName = loadedDocument.getName();
        final String documentContentType = findContentTypeInLowerCase(loadedDocument);
        return HEADER_VALUE_TEMPLATE.formatted(documentName, documentContentType);
    }

    private static String findContentTypeInLowerCase(final DownloadedFile loadedDocument) {
        final ContentType contentType = loadedDocument.getContentType();
        final String contentTypeAsString = contentType.name();
        return contentTypeAsString.toLowerCase();
    }
}
