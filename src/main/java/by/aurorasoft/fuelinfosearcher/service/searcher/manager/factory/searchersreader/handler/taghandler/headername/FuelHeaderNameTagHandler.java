package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.headername;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.headername.exception.NoSuchPropertyExtractorException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class FuelHeaderNameTagHandler extends TagHandler {
    private static final String TAG_NAME = "fuel-header-name";

    private final SpecificationPropertyExtractorDictionary propertyExtractorDictionary;

    public FuelHeaderNameTagHandler(final SpecificationPropertyExtractorDictionary propertyExtractorDictionary) {
        super(TAG_NAME);
        this.propertyExtractorDictionary = propertyExtractorDictionary;
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        final SpecificationPropertyExtractor propertyExtractor = this.findPropertyExtractor(context);
        context.accumulateFuelHeaderExtractor(propertyExtractor);
    }

    private SpecificationPropertyExtractor findPropertyExtractor(final FuelSearchersParsingContext context) {
        final String extractorKey = context.getLastContent();
        final Optional<SpecificationPropertyExtractor> optionalExtractor = this.propertyExtractorDictionary.find(
                extractorKey
        );
        return optionalExtractor.orElseThrow(NoSuchPropertyExtractorException::new);
    }

}
