package com.aurorasoft.fuelsearcher.service.searchingcontext;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.service.dictionary.FuelSearcherDictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.TableMetadataDictionary;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FuelSearchingContext {
    private final FuelDocument document;
    private final FuelSearcherDictionary searcherDictionary;
    private final SpecificationValidatorDictionary specificationValidatorDictionary;
    private final TableMetadataDictionary tableMetadataDictionary;
}
