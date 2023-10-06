package com.aurorasoft.fuelsearcher.service.tablesmetadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;

public interface TablesMetadataLoader {
    List<TableMetadata> load();
}
