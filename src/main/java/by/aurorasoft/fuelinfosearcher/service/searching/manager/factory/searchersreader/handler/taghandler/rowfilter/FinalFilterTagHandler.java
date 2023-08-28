package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FinalFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.FinalFilter;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

@Component
public final class FinalFilterTagHandler extends FilterTagHandler<FinalFilter, FinalFilterFactory> {
    private static final String TAG_NAME = "final-filter-by";

    public FinalFilterTagHandler(final FinalFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return empty();
    }

    @Override
    protected void accumulateFilter(final FuelSearchersParsingContext context, final FinalFilter filter) {
        context.accumulateFilter(filter);
    }
}
