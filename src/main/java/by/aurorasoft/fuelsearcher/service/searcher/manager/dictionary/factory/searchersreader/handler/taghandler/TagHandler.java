package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class TagHandler implements Translatable {
    private final String tagName;

    @Override
    public final String findAlias() {
        return this.tagName;
    }

    public abstract void handleStartTag(final SearchersParsingContext context);

    public abstract void handleEndTag(final SearchersParsingContext context);
}