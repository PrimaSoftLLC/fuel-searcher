package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import com.aurorasoft.fuelsearcher.service.dictionary.filter.interim.InterimFilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.factory.filter.interim.InterimFilterFactory;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.FilterTagHandler;
import com.aurorasoft.fuelsearcher.service.filter.interim.InterimFilter;

public abstract class InterimFilterTagHandler<
        FILTER extends InterimFilter,
        FILTER_FACTORY extends InterimFilterFactory<? extends FILTER, ?>
        >
        extends FilterTagHandler<FILTER, FILTER_FACTORY> {

    public InterimFilterTagHandler(final String tagName,
                                   final InterimFilterFactoryDictionary<FILTER_FACTORY> dictionary) {
        super(tagName, dictionary);
    }

    @Override
    protected final void accumulateFilter(final SearchersParsingContext context, final FILTER filter) {
        context.accumulateFilter(filter);
    }
}
