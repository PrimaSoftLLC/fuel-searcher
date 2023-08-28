package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.FilterTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimFilter;

public abstract class AbstractInterimFilterTagHandler<
        FILTER extends AbstractInterimFilter,
        FILTER_FACTORY extends InterimFilterFactory<FILTER>
        >
        extends FilterTagHandler<FILTER, FILTER_FACTORY> {

    public AbstractInterimFilterTagHandler(final String tagName,
                                           final FilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
        super(tagName, filterFactoryDictionary);
    }

    @Override
    protected final void accumulateFilter(FuelSearchersParsingContext context, FILTER filter) {
        context.accumulateFilter(filter);
    }
}
