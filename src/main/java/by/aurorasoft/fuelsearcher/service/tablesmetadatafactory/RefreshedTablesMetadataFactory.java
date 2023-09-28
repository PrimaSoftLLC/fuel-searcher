package by.aurorasoft.fuelsearcher.service.tablesmetadatafactory;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class RefreshedTablesMetadataFactory {

    public List<TableMetadata> create(final List<FuelSearcher> searchers) {

    }

    private static TableMetadata createAppropriateTableMetadata(final FuelSearcher searcher) {

    }
}
