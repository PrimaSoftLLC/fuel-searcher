package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType;
import com.aurorasoft.fuelsearcher.service.documentloading.DownloadingFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public final class DownloadingFileController {
    private static final String HEADER_KEY = "Content-disposition";
    private static final String HEADER_VALUE_TEMPLATE = "attachment; filename=%s.%s";

    private final DownloadingFileService downloadingService;

    @GetMapping("/fuelDocument")
    public ResponseEntity<StreamingResponseBody> downloadFuelDocument(final HttpServletResponse response) {
        return downloadFile(response, this.downloadingService::downloadFuelDocument);
    }

    @GetMapping("/searcherConfig")
    public ResponseEntity<StreamingResponseBody> downloadSearcherConfigFile(final HttpServletResponse response) {
        return downloadFile(response, this.downloadingService::downloadSearcherConfigFile);
    }

    private static ResponseEntity<StreamingResponseBody> downloadFile(final HttpServletResponse response,
                                                                      final Supplier<DownloadedFile> fileSupplier) {
        final DownloadedFile file = fileSupplier.get();
        setHeader(response, file);
        final byte[] fileBytes = file.getBytes();
        return ok(outputStream -> outputStream.write(fileBytes));
    }

    private static void setHeader(final HttpServletResponse response, final DownloadedFile file) {
        final String headerValue = findHeaderValue(file);
        response.setHeader(HEADER_KEY, headerValue);
    }

    private static String findHeaderValue(final DownloadedFile file) {
        final String fileName = file.getName();
        final String fileContentType = findContentTypeInLowerCase(file);
        return HEADER_VALUE_TEMPLATE.formatted(fileName, fileContentType);
    }

    private static String findContentTypeInLowerCase(final DownloadedFile file) {
        final ContentType contentType = file.getContentType();
        final String contentTypeAsString = contentType.name();
        return contentTypeAsString.toLowerCase();
    }
}
