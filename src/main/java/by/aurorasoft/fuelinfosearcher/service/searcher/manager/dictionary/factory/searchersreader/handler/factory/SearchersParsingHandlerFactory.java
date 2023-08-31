package by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.SearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.dictionary.TagHandlerDictionary;
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
