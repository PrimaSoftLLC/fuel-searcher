package com.aurorasoft.fuelsearcher.service.tablemetadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;

public interface TablesMetadataLoader {
    List<TableMetadata> load();
}
