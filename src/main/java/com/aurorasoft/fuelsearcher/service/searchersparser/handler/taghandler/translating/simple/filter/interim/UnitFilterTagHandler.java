package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit.UnitFilterFactory;
import com.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import com.aurorasoft.fuelsearcher.service.dictionary.filter.interim.UnitFilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

@Component
public final class UnitFilterTagHandler extends InterimFilterTagHandler<UnitFilter, UnitFilterFactory<?, ?>> {
    private static final String TAG_NAME = "filter-by";

    public UnitFilterTagHandler(final UnitFilterFactoryDictionary filterFactoryDictionary) {
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
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
