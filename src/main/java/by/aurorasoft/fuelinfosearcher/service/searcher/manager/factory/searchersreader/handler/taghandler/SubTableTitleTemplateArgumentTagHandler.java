package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import org.springframework.stereotype.Component;

//TODO: inherit from TranslatingTagHander
@Component
public final class SubTableTitleTemplateArgumentTagHandler extends TagHandler {
    private final SpecificationPropertyExtractorDictionary dictionary;

    public SubTableTitleTemplateArgumentTagHandler(final SpecificationPropertyExtractorDictionary dictionary) {
        super("sub-table-title-template-argument");
        this.dictionary = dictionary;
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.accumulateSubTableTitleTemplateArgumentExtractor(this.dictionary.find(context.getLastContent()).orElseThrow());
    }
}
