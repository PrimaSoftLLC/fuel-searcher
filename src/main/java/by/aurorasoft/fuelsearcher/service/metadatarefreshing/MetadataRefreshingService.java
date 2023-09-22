package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "meta-data-refreshing", name = "enable", havingValue = "true")
public class MetadataRefreshingService {
    private final TableMetadataService tableMetadataService;
    private final PropertyMetadataService propertyMetadataService;
    private final SearchersParsingResult parsingResult;

    @EventListener(ApplicationStartedEvent.class)
    public void refresh() {
        this.tableMetadataService.deleteAll();
        this.saveNewMetadata();
    }

    private void saveNewMetadata() {
        final List<TableMetadata> newTableMetadata = this.parsingResult.tablesMetadata();
        final List<TableMetadata> savedTableMetadata = this.tableMetadataService.saveAll(newTableMetadata);
        final List<PropertyMetadata> newPropertiesMetadata = findPropertiesMetadata(savedTableMetadata);
        this.propertyMetadataService.saveAll(newPropertiesMetadata);
    }

    private static List<PropertyMetadata> findPropertiesMetadata(final List<TableMetadata> tablesMetadata) {
        return tablesMetadata.stream()
                .flatMap(MetadataRefreshingService::findPropertiesMetadata)
                .toList();
    }

    private static Stream<PropertyMetadata> findPropertiesMetadata(final TableMetadata tableMetadata) {
        return tableMetadata.getPropertiesMetadata()
                .stream()
                .map(propertyMetadata -> replaceTableMetadata(propertyMetadata, tableMetadata));
    }

    private static PropertyMetadata replaceTableMetadata(final PropertyMetadata source,
                                                         final TableMetadata tableMetadata) {
        return new PropertyMetadata(
                source.getId(),
                source.getPropertyName(),
                source.getAllowableValues(),
                tableMetadata.getId()
        );
    }
}
