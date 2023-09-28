package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.stereotype.Component;

@Component
public final class SpecificationValidatorDictionary extends Dictionary<SpecificationValidator> {

    public SpecificationValidatorDictionary(final SearchersParsingResult searchersParsingResult) {
        super(searchersParsingResult.getSpecificationValidators());
    }

}
