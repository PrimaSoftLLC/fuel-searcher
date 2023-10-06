package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class PropertyMetadataSearchingManager {
    private static final String EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE
            = "Impossible to find metadata of property by given source '%s'";

    private final List<TablePropertyMetadataSearcher<?>> searchers;

    public PropertyMetadata find(final FuelTable fuelTable, final PropertyMetadataSource source) {
        return this.searchers.stream()
                .filter(searcher -> searcher.isAbleToFind(source))
                .findFirst()
                .map(searcher -> searcher.find(fuelTable, source))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE.formatted(source)
                        )
                );
    }
}
