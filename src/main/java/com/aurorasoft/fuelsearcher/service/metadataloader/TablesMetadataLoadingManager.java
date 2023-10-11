package com.aurorasoft.fuelsearcher.service.metadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.exception.TablesMetadataLoadingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class TablesMetadataLoadingManager {
    private final List<TablesMetadataLoader> metadataLoaders;

    public List<TableMetadata> load() {
        return this.metadataLoaders.stream()
                .filter(TablesMetadataLoader::isAbleToLoad)
                .findFirst()
                .map(TablesMetadataLoader::load)
                .orElseThrow(
                        () -> new TablesMetadataLoadingException("There is no loader which is able to load metadata")
                );
    }
}
