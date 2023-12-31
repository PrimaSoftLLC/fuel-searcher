package com.aurorasoft.fuelsearcher.service.dictionary;

import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SpecificationValidatorDictionary extends Dictionary<SpecificationValidator> {

    public SpecificationValidatorDictionary(final List<SpecificationValidator> validators) {
        super(validators);
    }

}
