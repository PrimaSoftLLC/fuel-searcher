package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.InterimRowFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimRowFilter;
import org.springframework.stereotype.Component;

@Component
public final class GroupFilterTagHandler extends AbstractInterimFilterTagHandler {
    private static final int DEFAULT_FILTRATION_CELL_INDEX = 0;

    public GroupFilterTagHandler(final String tagName, final InterimFilterFactoryDictionary filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected AbstractInterimRowFilter createFilter(final InterimRowFilterFactory filterFactory) {
        return filterFactory.apply(DEFAULT_FILTRATION_CELL_INDEX);
    }

}
