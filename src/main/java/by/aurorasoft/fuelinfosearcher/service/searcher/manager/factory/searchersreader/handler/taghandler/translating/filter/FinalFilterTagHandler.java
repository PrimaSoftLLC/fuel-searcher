package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.filter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FinalFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.filter.exception.DefaultFiltrationCellIndexNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import org.springframework.stereotype.Component;

@Component
public final class FinalFilterTagHandler extends FilterTagHandler<FinalFilter, FinalFilterFactory> {
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
}
