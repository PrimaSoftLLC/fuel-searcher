package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelsearcher.service.dictionary.filter.interim.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.interim.InterimFilterFactory;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple.filter.FilterTagHandler;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;

public abstract class AbstractInterimFilterTagHandler<
        FILTER extends InterimFilter,
        FILTER_FACTORY extends InterimFilterFactory<?, ?>
        >
        extends FilterTagHandler<FILTER, FILTER_FACTORY> {

    public AbstractInterimFilterTagHandler(final String tagName,
                                           final InterimFilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected final void accumulateFilter(final SearchersParsingContext context, final FILTER filter) {
        context.accumulateFilter(filter);
    }
}
