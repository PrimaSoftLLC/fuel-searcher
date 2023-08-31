package by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.unit.CorpusCountUnitFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.CorpusCountExtractor;
import org.springframework.stereotype.Component;

@Component
public final class CorpusCountUnitFilterFactory extends UnitFilterFactory<CorpusCountUnitFilter, CorpusCountExtractor> {

    public CorpusCountUnitFilterFactory(final CorpusCountExtractor corpusCountExtractor) {
        super(corpusCountExtractor);
    }

    @Override
    protected CorpusCountUnitFilter create(final CorpusCountExtractor corpusCountExtractor,
                                           final int filtrationCellIndex) {
        return new CorpusCountUnitFilter(corpusCountExtractor, filtrationCellIndex);
    }
}
