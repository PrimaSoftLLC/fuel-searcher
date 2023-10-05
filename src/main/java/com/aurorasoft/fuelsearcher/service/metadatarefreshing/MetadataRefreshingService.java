package com.aurorasoft.fuelsearcher.service.metadatarefreshing;

import com.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import com.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import com.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import com.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.RefreshedTablesMetadataFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "metadata-refreshing", name = "enable", havingValue = "true")
public final class MetadataRefreshingService {
    private final TableMetadataService tableMetadataService;
    private final PropertyMetadataService propertyMetadataService;
    private final RefreshedTablesMetadataFactory refreshedTablesMetadataFactory;

    @EventListener(ApplicationStartedEvent.class)
    public void refresh() {
        this.tableMetadataService.deleteAll();
        this.saveNewMetadata();
    }

    private void saveNewMetadata() {
        final List<TableMetadata> newTablesMetadata = this.refreshedTablesMetadataFactory.create();
        final List<TableMetadata> savedTablesMetadata = this.tableMetadataService.saveAll(newTablesMetadata);
        final List<PropertyMetadata> newPropertiesMetadata = findPropertiesMetadata(savedTablesMetadata);
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
