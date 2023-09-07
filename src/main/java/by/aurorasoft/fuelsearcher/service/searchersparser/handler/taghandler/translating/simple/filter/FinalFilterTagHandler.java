package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter;

import by.aurorasoft.fuelsearcher.service.dictionary.filter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.conclusive.FinalFilterFactory;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.exception.DefaultFiltrationCellIndexNotExistException;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import org.springframework.stereotype.Component;

@Component
public final class FinalFilterTagHandler extends FilterTagHandler<FinalFilter, FinalFilterFactory<?, ?>> {
    private static final String TAG_NAME = "final-filter-by";

    public FinalFilterTagHandler(final FinalFilterFactoryDictionary filterFactoryDictionary) {
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
    protected void accumulateFilter(final SearchersParsingContext context, final FinalFilter filter) {
        context.accumulateFilter(filter);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
