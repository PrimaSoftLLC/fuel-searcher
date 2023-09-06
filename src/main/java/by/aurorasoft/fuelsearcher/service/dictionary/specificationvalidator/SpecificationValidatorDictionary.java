package by.aurorasoft.fuelsearcher.service.dictionary.specificationvalidator;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;

import java.util.List;

public final class SpecificationValidatorDictionary extends Dictionary<SpecificationValidator> {

    public SpecificationValidatorDictionary(final List<SpecificationValidator> validators) {
        super(validators);
    }

}
