package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelsearcher.service.dictionary.filter.interim.GroupFilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.interim.group.GroupFilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.context.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class GroupFilterTagHandler extends AbstractInterimFilterTagHandler<GroupFilter, GroupFilterFactory<?, ?>> {
    private static final String TAG_NAME = "filter-by-group";
    private static final int DEFAULT_FILTRATION_CELL_INDEX = 0;

    public GroupFilterTagHandler(final GroupFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected int findDefaultFiltrationCellIndex() {
        return DEFAULT_FILTRATION_CELL_INDEX;
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
