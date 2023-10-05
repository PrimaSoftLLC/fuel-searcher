package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import com.aurorasoft.fuelsearcher.service.dictionary.filter.interim.GroupFilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.factory.filter.interim.group.GroupFilterFactory;
import com.aurorasoft.fuelsearcher.service.filter.interim.group.GroupFilter;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public final class GroupFilterTagHandler extends InterimFilterTagHandler<GroupFilter, GroupFilterFactory<?, ?>> {
    private static final String TAG_NAME = "filter-by-group";
    private static final int DEFAULT_FILTRATION_CELL_INDEX = 0;

    public GroupFilterTagHandler(final GroupFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return OptionalInt.of(DEFAULT_FILTRATION_CELL_INDEX);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
