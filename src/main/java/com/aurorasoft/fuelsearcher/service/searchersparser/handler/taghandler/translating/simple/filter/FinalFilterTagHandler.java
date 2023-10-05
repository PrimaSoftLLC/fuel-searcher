package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter;

import com.aurorasoft.fuelsearcher.service.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.service.factory.filter.conclusive.FinalFilterFactory;
import com.aurorasoft.fuelsearcher.service.dictionary.filter.FinalFilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

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
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return empty();
    }

    @Override
    protected void accumulateFilter(final SearchersParsingContext context, final FinalFilter filter) {
        context.accumulateFilter(filter);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
