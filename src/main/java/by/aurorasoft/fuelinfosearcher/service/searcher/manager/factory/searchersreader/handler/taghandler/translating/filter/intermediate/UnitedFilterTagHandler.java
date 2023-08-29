package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.filter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.UnitedFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.UnitedFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.filter.exception.DefaultFiltrationCellIndexNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.united.UnitedFilter;
import org.springframework.stereotype.Component;

@Component
public final class UnitedFilterTagHandler extends AbstractInterimFilterTagHandler<UnitedFilter, UnitedFilterFactory> {
    private static final String TAG_NAME = "filter-by";

    public UnitedFilterTagHandler(final UnitedFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    protected int findDefaultFiltrationCellIndex() {
        throw new DefaultFiltrationCellIndexNotExistException();
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }
}