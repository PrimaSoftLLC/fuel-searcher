package by.aurorasoft.fuelsearcher.service.searcherparser;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import lombok.Value;

import java.util.List;

@Value
public class SearchersParsingResult {
    List<FuelSearcher> searchers;
    List<SpecificationValidator> specificationValidators;
}
