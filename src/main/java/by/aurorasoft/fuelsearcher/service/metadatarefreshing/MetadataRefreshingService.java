package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: test
@Service
@RequiredArgsConstructor
public class MetadataRefreshingService {
    private final TableMetadataService tableMetadataService;
    private final PropertyMetadataService propertyMetadataService;
    private final SearchersParsingResult parsingResult;

    @Transactional
//    @EventListener(ApplicationStartedEvent.class)
    public void refresh() {
        this.tableMetadataService.deleteAll();
        this.saveNewMetadata();
    }

    private void saveNewMetadata() {
        final List<TableMetadata> newMetadata = this.parsingResult.tablesMetadata();
        final List<TableMetadata> newSavedMetadata = this.tableMetadataService.saveAll(newMetadata);
        newSavedMetadata.forEach(tableMetadata -> this.propertyMetadataService.saveAll(tableMetadata.getPropertiesMetadata()));
    }
}
