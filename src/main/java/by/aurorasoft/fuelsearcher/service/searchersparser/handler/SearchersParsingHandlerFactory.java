package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContextFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class SearchersParsingHandlerFactory {
    private final SearchersParsingContextFactory contextFactory;
    private final TagHandlerDictionary handlerDictionary;

    public SearchersParsingHandler create() {
        final SearchersParsingContext context = this.contextFactory.create();
        return new SearchersParsingHandler(this.handlerDictionary, context);
    }
}
