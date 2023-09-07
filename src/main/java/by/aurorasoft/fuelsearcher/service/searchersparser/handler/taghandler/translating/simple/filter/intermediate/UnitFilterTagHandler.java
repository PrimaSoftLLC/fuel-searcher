package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.intermediate;

import by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit.UnitFilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import by.aurorasoft.fuelsearcher.service.dictionary.filter.interim.UnitFilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

@Component
public final class UnitFilterTagHandler extends AbstractInterimFilterTagHandler<UnitFilter, UnitFilterFactory<?, ?>> {
    private static final String TAG_NAME = "filter-by";

    public UnitFilterTagHandler(final UnitFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return empty();
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
