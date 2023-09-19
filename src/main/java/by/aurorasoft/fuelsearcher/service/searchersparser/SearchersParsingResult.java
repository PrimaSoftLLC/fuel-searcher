package by.aurorasoft.fuelsearcher.service.searchersparser;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;

import java.util.List;

public record SearchersParsingResult(List<FuelSearcher> searchers,
                                     List<SpecificationValidator> specificationValidators,
                                     List<TableMetadata> tablesMetadata) {
}
