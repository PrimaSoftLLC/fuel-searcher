package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.metadata.TablePropertyMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class TablePropertyMetadataSearchingManager {
    private static final String EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE
            = "Impossible to find metadata of property by given source '%s'";

    private final List<TablePropertyMetadataSearcher<?>> searchers;

    public TablePropertyMetadata find(final FuelTable table, final PropertyMetadataSource source) {
        return this.searchers.stream()
                .filter(searcher -> searcher.isAbleToFind(source))
                .findFirst()
                .map(searcher -> searcher.find(table, source))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE.formatted(source)
                        )
                );
    }
}
