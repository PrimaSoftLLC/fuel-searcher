package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import org.springframework.stereotype.Component;

@Component
public final class FuelTableDictionary extends Dictionary<FuelTable> {

    public FuelTableDictionary(final FuelDocument document) {
        super(document.getTables());
    }

}
