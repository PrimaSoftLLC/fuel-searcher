package com.aurorasoft.fuelsearcher.service.dictionary;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import org.springframework.stereotype.Component;

@Component
public final class FuelTableDictionary extends Dictionary<FuelTable> {

    public FuelTableDictionary(final FuelDocument document) {
        super(document.tables());
    }

}
