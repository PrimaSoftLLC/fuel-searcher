package com.aurorasoft.fuelsearcher.service.validator;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class SpecificationValidatingManager {
    private final SpecificationValidatorDictionary validatorDictionary;
    private final TableNameExtractor tableNameExtractor;

    public SpecificationValidatingResult validate(final FuelSpecification specification) {
        return this.tableNameExtractor.find(specification)
                .flatMap(this.validatorDictionary::find)
                .map(validator -> validator.validate(specification))
                .orElseGet(() -> SpecificationValidatingResult.createNotValidValidatingResult(this.tableNameExtractor));
    }
}
