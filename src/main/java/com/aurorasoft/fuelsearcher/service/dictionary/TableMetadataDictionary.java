package com.aurorasoft.fuelsearcher.service.dictionary;


import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class TableMetadataDictionary extends Dictionary<TableMetadata> {

    public TableMetadataDictionary(final List<TableMetadata> tablesMetadata) {
        super(tablesMetadata);
    }

}
