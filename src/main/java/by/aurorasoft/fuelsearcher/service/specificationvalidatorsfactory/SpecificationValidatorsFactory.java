package by.aurorasoft.fuelsearcher.service.specificationvalidatorsfactory;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SpecificationValidatorsFactory {

    public List<SpecificationValidator> create(final List<FuelSearcher> searchers) {
        return searchers.stream()
                .map(SpecificationValidatorsFactory::createAppropriateValidator)
                .toList();
    }

    private static SpecificationValidator createAppropriateValidator(final FuelSearcher searcher) {
        final String tableName = searcher.findTableName();
        final List<SpecificationPropertyExtractor> requiredPropertyExtractors = searcher.findPropertyExtractors();
        return new SpecificationValidator(tableName, requiredPropertyExtractors);
    }
}
