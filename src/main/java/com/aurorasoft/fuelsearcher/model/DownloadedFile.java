package com.aurorasoft.fuelsearcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

//TODO: rename load to download
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
