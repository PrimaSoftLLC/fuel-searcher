package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO: RowFilter rename to Filter everywhere
@RequiredArgsConstructor
@Getter
public abstract class TagHandler {
    private final String tagName;

    public abstract void handleStartTag(final SearchersParsingContext context);

    public abstract void handleEndTag(final SearchersParsingContext context);
}
