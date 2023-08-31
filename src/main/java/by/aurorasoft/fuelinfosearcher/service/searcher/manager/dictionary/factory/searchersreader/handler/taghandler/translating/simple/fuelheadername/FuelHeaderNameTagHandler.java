package by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple.fuelheadername;

import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.dictionary.FuelHeaderMetadataDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple.SimpleTranslatingTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple.fuelheadername.exception.NoSuchFuelHeaderNameException;
import org.springframework.stereotype.Component;

@Component
public final class FuelHeaderNameTagHandler extends SimpleTranslatingTagHandler<FuelHeaderMetadata> {
    private static final String TAG_NAME = "fuel-header-name";

    public FuelHeaderNameTagHandler(final FuelHeaderMetadataDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchFuelHeaderNameException::new);
    }


    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected void accumulateTranslatedValue(final SearchersParsingContext context,
                                             final FuelHeaderMetadata headerMetadata) {
        context.accumulateFuelHeaderMetaData(headerMetadata);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }
}
