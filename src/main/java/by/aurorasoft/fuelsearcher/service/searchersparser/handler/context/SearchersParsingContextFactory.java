package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearchingManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class SearchersParsingContextFactory {
    private final PropertyMetadataSearchingManager propertyMetadataSearchingManager;

    public SearchersParsingContext create() {
        return new SearchersParsingContext(this.propertyMetadataSearchingManager);
    }
}
