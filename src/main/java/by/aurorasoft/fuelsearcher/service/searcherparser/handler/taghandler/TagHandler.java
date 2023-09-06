package by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcherparser.handler.SearchersParsingContext;
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
