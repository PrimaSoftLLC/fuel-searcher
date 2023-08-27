package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.RowFilterByCellIndexFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.AbstractDictionary;

import java.util.Map;

public abstract class AbstractRowFilterFactoryDictionary<F extends RowFilterByCellIndexFactory<?>>
        extends AbstractDictionary<F> {

    public AbstractRowFilterFactoryDictionary(final Map<String, F> valuesByKeys) {
        super(valuesByKeys);
    }

}
