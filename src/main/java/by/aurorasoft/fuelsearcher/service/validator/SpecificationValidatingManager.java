package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult.createNotValidValidatingResult;

@Service
@RequiredArgsConstructor
public final class SpecificationValidatingManager {
    private final SpecificationValidatorDictionary validatorDictionary;
    private final TableNameExtractor tableNameExtractor;

    public SpecificationValidatingResult validate(final FuelSpecification specification) {
        return this.tableNameExtractor.findProperty(specification)
                .flatMap(this.validatorDictionary::find)
                .map(validator -> validator.validate(specification))
                .orElseGet(() -> createNotValidValidatingResult(this.tableNameExtractor));
    }
}
