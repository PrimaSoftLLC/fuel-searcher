package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.translating.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.GroupFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.GroupFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.GroupFilter;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public final class GroupFilterTagHandler extends AbstractInterimFilterTagHandler<GroupFilter, GroupFilterFactory> {
    private static final String TAG_NAME = "filter-by-group";
    private static final int DEFAULT_FILTRATION_CELL_INDEX = 0;

    public GroupFilterTagHandler(final GroupFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return OptionalInt.of(DEFAULT_FILTRATION_CELL_INDEX);
    }

}
