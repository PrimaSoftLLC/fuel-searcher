package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FinalFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.rowfilter.exception.DefaultFiltrationCellIndexNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import org.springframework.stereotype.Component;

@Component
public final class FinalFilterTagHandler extends FilterTagHandler<FinalFilter, FinalFilterFactory> {
    private static final String TAG_NAME = "final-filter-by";

    public FinalFilterTagHandler(final FinalFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    protected int findDefaultFiltrationCellIndex() {
        throw new DefaultFiltrationCellIndexNotExistException();
    }

    @Override
    protected void accumulateFilter(final FuelSearchersParsingContext context, final FinalFilter filter) {
        context.accumulateFilter(filter);
    }
}
