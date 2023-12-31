package com.aurorasoft.fuelsearcher.service.metadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;

public interface TablesMetadataLoader {
    boolean isAbleToLoad();
    List<TableMetadata> load();
}
