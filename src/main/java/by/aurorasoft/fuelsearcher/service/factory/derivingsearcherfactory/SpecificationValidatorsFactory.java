package by.aurorasoft.fuelsearcher.service.factory.derivingsearcherfactory;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SpecificationValidatorsFactory extends DerivingSearcherFactory<SpecificationValidator> {

    public SpecificationValidatorsFactory(final List<FuelSearcher> searchers) {
        super(searchers);
    }

    @Override
    protected SpecificationValidator createDerivedObject(final FuelSearcher searcher) {
        final String tableName = searcher.findTableName();
        final List<SpecificationPropertyExtractor> requiredPropertyExtractors = searcher.findPropertyExtractors();
        return new SpecificationValidator(tableName, requiredPropertyExtractors);
    }
}
