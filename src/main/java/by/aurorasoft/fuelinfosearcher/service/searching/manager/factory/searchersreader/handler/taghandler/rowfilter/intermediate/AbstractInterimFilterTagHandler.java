package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.AbstractFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.AbstractFilterTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimFilter;

public abstract class AbstractInterimFilterTagHandler<
        FILTER extends AbstractInterimFilter,
        FILTER_FACTORY extends InterimFilterFactory<FILTER>
        >
        extends AbstractFilterTagHandler<FILTER, FILTER_FACTORY> {

    public AbstractInterimFilterTagHandler(final String tagName,
                                           final AbstractFilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected final void accumulateFilter(FuelSearchersParsingContext context, FILTER filter) {
        context.accumulateFilter(filter);
    }
}
