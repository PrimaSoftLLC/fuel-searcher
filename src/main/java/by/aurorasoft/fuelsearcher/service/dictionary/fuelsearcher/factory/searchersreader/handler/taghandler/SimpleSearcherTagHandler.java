package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.context.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class SimpleSearcherTagHandler extends TagHandler {
    private static final String TAG_NAME = "simple-searcher";

    public SimpleSearcherTagHandler() {
        super(TAG_NAME);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {
        context.startBuildSimpleSearcher();
    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.buildSimpleSearcher();
    }

}
