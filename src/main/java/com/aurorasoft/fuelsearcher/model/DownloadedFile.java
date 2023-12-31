package com.aurorasoft.fuelsearcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class DownloadedFile {
    byte[] bytes;
    String name;
    ContentType contentType;

    public enum ContentType {
        XML, DOCX
    }
}
