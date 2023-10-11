package com.aurorasoft.fuelsearcher.service.metadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
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
                .orElseThrow(NoSuitableMetadataLoaderException::new);
    }

    private static final class NoSuitableMetadataLoaderException extends RuntimeException {

        public NoSuitableMetadataLoaderException() {

        }

        @SuppressWarnings("unused")
        public NoSuitableMetadataLoaderException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoSuitableMetadataLoaderException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuitableMetadataLoaderException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
