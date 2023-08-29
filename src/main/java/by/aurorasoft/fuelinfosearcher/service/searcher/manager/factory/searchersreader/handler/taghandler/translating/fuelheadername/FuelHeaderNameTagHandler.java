package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.fuelheadername;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.TranslatingTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.fuelheadername.exception.NoSuchFuelHeaderNameException;
import org.springframework.stereotype.Component;

@Component
public final class FuelHeaderNameTagHandler extends TranslatingTagHandler<SpecificationPropertyExtractor> {
    private static final String TAG_NAME = "fuel-header-name";

    public FuelHeaderNameTagHandler(final SpecificationPropertyExtractorDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchFuelHeaderNameException::new);
    }


    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected void handleValue(final SearchersParsingContext context, final SpecificationPropertyExtractor extractor) {
        context.accumulateFuelHeaderExtractor(extractor);
    }
}
