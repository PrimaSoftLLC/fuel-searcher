package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class CompositeSearcherTagHandler extends TagHandler {
    private static final String TAG_HANDLER = "composite-searcher";

    public CompositeSearcherTagHandler() {
        super(TAG_HANDLER);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {
        context.startParsingCompositeSearcher();
    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.buildAndAccumulateCompositeSearcher();
    }
}
