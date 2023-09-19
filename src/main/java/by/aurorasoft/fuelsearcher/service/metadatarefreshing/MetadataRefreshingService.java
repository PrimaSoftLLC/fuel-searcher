package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MetadataRefreshingService {
    private final PropertyMetadataService propertyMetadataService;
    private final TableMetadataService tableMetadataService;
    private final SearchersParsingResult parsingResult;

    @Transactional
    public void refresh() {
        this.propertyMetadataService.deleteAll();

    }
}
