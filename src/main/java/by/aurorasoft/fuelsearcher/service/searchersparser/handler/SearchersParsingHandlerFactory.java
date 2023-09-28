package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class SearchersParsingHandlerFactory {
    private final TagHandlerDictionary handlerDictionary;

    public SearchersParsingHandler create() {
        final SearchersParsingContext context = new SearchersParsingContext();
        return new SearchersParsingHandler(this.handlerDictionary, context);
    }
}
