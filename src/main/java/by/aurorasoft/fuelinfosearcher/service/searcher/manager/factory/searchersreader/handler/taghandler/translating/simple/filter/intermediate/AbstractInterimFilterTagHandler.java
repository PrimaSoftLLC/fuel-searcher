package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.filter.FilterTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.intermediate.InterimFilter;

public abstract class AbstractInterimFilterTagHandler<
        FILTER extends InterimFilter,
        FILTER_FACTORY extends InterimFilterFactory<FILTER>
        >
        extends FilterTagHandler<FILTER, FILTER_FACTORY> {

    public AbstractInterimFilterTagHandler(final String tagName,
                                           final FilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected final void accumulateFilter(final SearchersParsingContext context, final FILTER filter) {
        context.accumulateFilter(filter);
    }
}
