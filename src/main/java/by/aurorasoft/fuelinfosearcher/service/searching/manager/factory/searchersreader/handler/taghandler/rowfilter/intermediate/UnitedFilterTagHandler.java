package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.InterimFilterFactoryDictionary;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

@Component
public final class UnitedFilterTagHandler extends AbstractInterimFilterTagHandler {

    public UnitedFilterTagHandler(final String tagName, final InterimFilterFactoryDictionary filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return empty();
    }

}
