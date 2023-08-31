package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class CompositeSearcherTagHandler extends TagHandler {
    private static final String TAG_HANDLER = "composite-searcher";

    public CompositeSearcherTagHandler() {
        super(TAG_HANDLER);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {
        context.startBuildCompositeSearcher();
    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.buildCompositeSearcher();
    }
}
