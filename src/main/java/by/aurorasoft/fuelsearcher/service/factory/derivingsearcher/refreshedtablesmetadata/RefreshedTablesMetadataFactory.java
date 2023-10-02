package by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.DerivingSearcherFactory;
import by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearchingManager;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class RefreshedTablesMetadataFactory extends DerivingSearcherFactory<TableMetadata> {
    private final PropertyMetadataSearchingManager propertyMetadataSearchingManager;

    public RefreshedTablesMetadataFactory(final List<FuelSearcher> searchers,
                                          final PropertyMetadataSearchingManager propertyMetadataSearchingManager) {
        super(searchers);
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
    }

    @Override
    protected TableMetadata createDerivedObject(final FuelSearcher searcher) {
        final String tableName = searcher.findTableName();
        final List<PropertyMetadata> propertiesMetadata = this.findPropertiesMetadata(searcher);
        return createTableMetadata(tableName, propertiesMetadata);
    }

    private List<PropertyMetadata> findPropertiesMetadata(final FuelSearcher searcher) {
        final FuelTable table = searcher.getTable();
        return searcher.findPropertyMetadataSources()
                .map(metadataSource -> this.propertyMetadataSearchingManager.find(table, metadataSource))
                .toList();
    }

    private static TableMetadata createTableMetadata(final String tableName,
                                                     final List<PropertyMetadata> propertiesMetadata) {
        return TableMetadata.builder()
                .tableName(tableName)
                .propertiesMetadata(propertiesMetadata)
                .build();
    }
}
