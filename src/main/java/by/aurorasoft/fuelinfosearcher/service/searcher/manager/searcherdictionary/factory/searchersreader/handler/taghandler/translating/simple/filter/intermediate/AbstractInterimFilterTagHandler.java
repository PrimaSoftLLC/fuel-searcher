package by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelinfosearcher.dictionary.filter.interim.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.simple.filter.FilterTagHandler;
import by.aurorasoft.fuelinfosearcher.model.filter.interim.InterimFilter;

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
