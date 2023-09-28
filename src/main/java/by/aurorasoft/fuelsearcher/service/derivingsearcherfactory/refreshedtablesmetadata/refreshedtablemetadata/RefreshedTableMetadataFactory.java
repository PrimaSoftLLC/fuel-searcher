package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.refreshedtablemetadata;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RefreshedTableMetadataFactory<S extends FuelSearcher> {
    private final Class<S> searcherType;

    public final boolean isAbleToCreate(final FuelSearcher searcher) {
        return this.searcherType.isInstance(searcher);
    }

    public final TableMetadata create(final FuelSearcher searcher) {
        final S concreteSearcher = this.searcherType.cast(searcher);
    }
}
