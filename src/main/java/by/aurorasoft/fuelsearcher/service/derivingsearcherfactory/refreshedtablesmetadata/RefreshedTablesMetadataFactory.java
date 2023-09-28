package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.DerivingSearcherFactory;
import by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.refreshedtablemetadata.RefreshedTableMetadataCreatingManager;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class RefreshedTablesMetadataFactory extends DerivingSearcherFactory<TableMetadata> {
    private final RefreshedTableMetadataCreatingManager metadataCreatingManager;

    public RefreshedTablesMetadataFactory(final List<FuelSearcher> searchers,
                                          final RefreshedTableMetadataCreatingManager metadataCreatingManager) {
        super(searchers);
        this.metadataCreatingManager = metadataCreatingManager;
    }

    @Override
    protected TableMetadata createDerivedObject(final FuelSearcher searcher) {
        return this.metadataCreatingManager.create(searcher);
    }
}
