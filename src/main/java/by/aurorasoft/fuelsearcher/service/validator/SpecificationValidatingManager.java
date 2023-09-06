package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class SpecificationValidatingManager {
    private final SpecificationValidatorDictionary validatorDictionary;

    public boolean isValid(final FuelSpecification specification) {
//        return specification.findTableName()
//                .flatMap(this.validatorDictionary::find)
//                .map(validator -> validator.validate(specification))
//                .orElse(false);
        return null;
    }
}
