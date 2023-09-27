package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
public final class PropertyMetadataSearchingManager {
    private static final String EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE
            = "Impossible to find metadata of property by given source '%s'";

    private final List<PropertyMetadataSearcher<?>> searchers;

    private final boolean searchingRequired;

    public PropertyMetadataSearchingManager(final List<PropertyMetadataSearcher<?>> searchers,
                                            @Value("${metadata-refreshing.enable}") final boolean metadataRefreshingEnabled) {
        this.searchers = searchers;
        this.searchingRequired = metadataRefreshingEnabled;
    }

    public Optional<PropertyMetadata> findIfNecessary(final FuelTable fuelTable, final PropertyMetadataSource source) {
        return this.searchingRequired ? Optional.of(this.find(fuelTable, source)) : empty();
    }

    private PropertyMetadata find(final FuelTable fuelTable, final PropertyMetadataSource source) {
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
