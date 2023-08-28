package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.InterimRowFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate.exception.FiltrationCellIndexNotDefinedException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimRowFilter;
import org.springframework.stereotype.Component;

@Component
public final class UnitedFilterTagHandler extends AbstractInterimFilterTagHandler {

    public UnitedFilterTagHandler(final String tagName, final InterimFilterFactoryDictionary filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected AbstractInterimRowFilter createFilter(final InterimRowFilterFactory filterFactory) {
        throw new FiltrationCellIndexNotDefinedException();
    }
}
