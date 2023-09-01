package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelsearcher.service.dictionary.filter.interim.UnitFilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit.UnitFilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.taghandler.translating.simple.filter.exception.DefaultFiltrationCellIndexNotExistException;
import org.springframework.stereotype.Component;

@Component
public final class UnitFilterTagHandler extends AbstractInterimFilterTagHandler<UnitFilter, UnitFilterFactory<?, ?>> {
    private static final String TAG_NAME = "filter-by";

    public UnitFilterTagHandler(final UnitFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected int findDefaultFiltrationCellIndex() {
        throw new DefaultFiltrationCellIndexNotExistException();
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
