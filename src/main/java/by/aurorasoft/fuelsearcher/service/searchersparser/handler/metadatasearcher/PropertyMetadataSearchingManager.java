package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class PropertyMetadataSearchingManager {
    private static final String EXCEPTION_DESCRIPTION_NOT_SUITABLE_SOURCE
            = "Impossible to find metadata of property by given source '%s'";

    private final List<PropertyMetadataSearcher<?>> searchers;

    public PropertyMetadata find(final FuelTable fuelTable, final Object source) {
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
