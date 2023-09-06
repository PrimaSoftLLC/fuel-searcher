package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler.dictionary.TagHandlerDictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler.taghandler.TagHandler;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.result.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Optional;
import java.util.function.BiConsumer;

import static java.lang.String.copyValueOf;

@RequiredArgsConstructor
public final class SearchersParsingHandler extends DefaultHandler {
    private final TagHandlerDictionary handlerDictionary;
    private final SearchersParsingContext context;

    public SearchersParsingResult findParsingResult() {
        return this.context.findResult();
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

    private void handleTag(final String tagName, final BiConsumer<TagHandler, SearchersParsingContext> handlingFunction) {
        final Optional<TagHandler> optionalHandler = this.handlerDictionary.find(tagName);
        optionalHandler.ifPresent(handler -> handlingFunction.accept(handler, this.context));
    }
}
