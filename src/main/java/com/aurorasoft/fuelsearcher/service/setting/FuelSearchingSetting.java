package com.aurorasoft.fuelsearcher.service.setting;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.service.dictionary.FuelSearcherDictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.TableMetadataDictionary;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public final class FuelSearchingSetting {
    private final FuelDocument document;
    private final FuelSearcherDictionary searcherDictionary;
    private final SpecificationValidatorDictionary specificationValidatorDictionary;
    private final TableMetadataDictionary tableMetadataDictionary;
}
