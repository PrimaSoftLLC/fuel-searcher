package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.TagHandler;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.copyValueOf;
import static java.util.Optional.ofNullable;

public final class FuelSearchersParsingHandler extends DefaultHandler {
    private final Map<String, TagHandler> handlersByTagNames;
    private final FuelSearchersParsingContext context;

    public FuelSearchersParsingHandler(final Map<String, TagHandler> handlersByTagNames,
                                       final FuelSearchersParsingContext context) {
        this.handlersByTagNames = handlersByTagNames;
        this.context = context;
    }

    public List<AbstractTableFuelSearcher> findParsedSearchers() {
        return this.context.getParsedSearchers();
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qualifiedName,
                             final Attributes attributes) {
        this.context.setLastAttributes(attributes);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qualifiedName) {
        final Optional<TagHandler> optionalHandler = this.findHandler(qualifiedName);
        optionalHandler.ifPresent(handler -> handler.handle(this.context));
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) {
        final String copy = copyValueOf(chars, start, length);
        final String trimmedCopy = copy.trim();
        this.context.setLastContent(trimmedCopy);
    }

    private Optional<TagHandler> findHandler(final String tagName) {
        final TagHandler handler = this.handlersByTagNames.get(tagName);
        return ofNullable(handler);
    }
}
