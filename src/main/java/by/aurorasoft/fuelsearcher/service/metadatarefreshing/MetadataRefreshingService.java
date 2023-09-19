package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: test
@Service
@RequiredArgsConstructor
public class MetadataRefreshingService {
    private final TableMetadataService tableMetadataService;
    private final SearchersParsingResult parsingResult;

    @Transactional
    public void refresh() {
        this.tableMetadataService.deleteAll();
        this.saveNewMetadata();
    }

    private void saveNewMetadata() {
        final List<TableMetadata> newMetadata = this.parsingResult.tablesMetadata();
        this.tableMetadataService.saveAll(newMetadata);
    }
}
