package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public final class FuelSearchersParsingHandlerFactory {
    private final Map<String, TagHandler> handlersByTagNames;

    public FuelSearchersParsingHandlerFactory(final List<TagHandler> tagHandlers) {
        this.handlersByTagNames = createHandlersByTagNames(tagHandlers);
    }

    public FuelSearchersParsingHandler create() {
        final FuelSearchersParsingContext context = new FuelSearchersParsingContext();
        return new FuelSearchersParsingHandler(this.handlersByTagNames, context);
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
