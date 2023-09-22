package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class CompositeSearcherTagHandler extends TagHandler {
    private static final String TAG_HANDLER = "composite-searcher";

    public CompositeSearcherTagHandler() {
        super(TAG_HANDLER);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {
        context.startParseCompositeSearcher();
    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.buildCompositeSearcher();
    }
}
