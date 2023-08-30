package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.GroupFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.GroupFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.intermediate.group.GroupFilter;
import org.springframework.stereotype.Component;

@Component
public final class GroupFilterTagHandler extends AbstractInterimFilterTagHandler<GroupFilter, GroupFilterFactory> {
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
