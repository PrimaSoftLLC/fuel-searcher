package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler;

import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import static java.lang.String.copyValueOf;
import static java.util.Optional.ofNullable;

public final class SearchersParsingHandler extends DefaultHandler {
    private final Map<String, TagHandler> handlersByTagNames;
    private final SearchersParsingContext context;

    public SearchersParsingHandler(final Map<String, TagHandler> handlersByTagNames,
                                   final SearchersParsingContext context) {
        this.handlersByTagNames = handlersByTagNames;
        this.context = context;
    }

    public List<FuelSearcher> findParsedSearchers() {
        return this.context.getParsedSearchers();
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qualifiedName,
                             final Attributes attributes) {
        this.context.setLastAttributes(attributes);
        this.handleTag(qualifiedName, TagHandler::handleStartTag);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qualifiedName) {
        this.handleTag(qualifiedName, TagHandler::handleEndTag);
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) {
        final String copy = copyValueOf(chars, start, length);
        final String trimmedCopy = copy.trim();
        this.context.setLastContent(trimmedCopy);
    }

    private void handleTag(final String tagName,
                           final BiConsumer<TagHandler, SearchersParsingContext> handlingFunction) {
        final Optional<TagHandler> optionalHandler = this.findHandler(tagName);
        optionalHandler.ifPresent(handler -> handlingFunction.accept(handler, this.context));
    }

    private Optional<TagHandler> findHandler(final String tagName) {
        final TagHandler handler = this.handlersByTagNames.get(tagName);
        return ofNullable(handler);
    }
}
