package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.result;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import lombok.Value;

import java.util.List;

@Value
public class SearchersParsingResult {
    List<FuelSearcher> searchers;
    List<SpecificationValidator> specificationValidators;
}
