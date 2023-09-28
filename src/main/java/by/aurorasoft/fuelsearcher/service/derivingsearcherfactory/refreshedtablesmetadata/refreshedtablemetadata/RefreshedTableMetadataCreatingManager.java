package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.refreshedtablemetadata;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class RefreshedTableMetadataCreatingManager {
    private final List<RefreshedTableMetadataFactory> factories;

    public TableMetadata create(final FuelSearcher searcher) {

    }
}
