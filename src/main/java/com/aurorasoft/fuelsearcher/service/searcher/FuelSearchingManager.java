package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.FuelSearcherDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public final class FuelSearchingManager {
    private final FuelSearcherDictionary searcherDictionary;
    private final TableNameExtractor tableNameExtractor;

    public Optional<Fuel> find(final FuelSpecification specification) {
        final String tableName = this.tableNameExtractor.extract(specification);
        final Optional<FuelSearcher> optionalSearcher = this.searcherDictionary.find(tableName);
        return optionalSearcher.flatMap(searcher -> searcher.find(specification));
    }
}
