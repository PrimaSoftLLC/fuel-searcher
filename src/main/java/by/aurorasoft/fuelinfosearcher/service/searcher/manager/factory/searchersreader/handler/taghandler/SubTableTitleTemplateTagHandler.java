package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class SubTableTitleTemplateTagHandler extends TagHandler {
    private static final String TAG_NAME = "sub-table-title-template";

    public SubTableTitleTemplateTagHandler() {
        super(TAG_NAME);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        context.accumulateSubTableTitleTemplate();
    }
}
