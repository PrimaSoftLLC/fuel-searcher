package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.SearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public final class SearchersParsingHandlerFactory {
    private final Map<String, TagHandler> handlersByTagNames;

    public SearchersParsingHandlerFactory(final List<TagHandler> tagHandlers) {
        this.handlersByTagNames = createHandlersByTagNames(tagHandlers);
    }

    public SearchersParsingHandler create() {
        final SearchersParsingContext context = new SearchersParsingContext();
        return new SearchersParsingHandler(this.handlersByTagNames, context);
    }

    private static Map<String, TagHandler> createHandlersByTagNames(final List<TagHandler> tagHandlers) {
        return tagHandlers.stream()
                .collect(
                        toMap(
                                TagHandler::getTagName,
                                identity()
                        )
                );
    }
}
