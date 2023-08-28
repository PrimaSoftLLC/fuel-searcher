package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.InterimFilterFactoryDictionary;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public final class GroupFilterTagHandler extends AbstractInterimFilterTagHandler {
    private static final int DEFAULT_FILTRATION_CELL_INDEX = 0;

    public GroupFilterTagHandler(final String tagName, final InterimFilterFactoryDictionary filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return OptionalInt.of(DEFAULT_FILTRATION_CELL_INDEX);
    }

}
