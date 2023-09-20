package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.TranslatingTagHandler;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

import java.util.stream.Stream;

public abstract class SimpleTranslatingTagHandler<V extends Translatable<String>> extends TranslatingTagHandler<V> {

    public SimpleTranslatingTagHandler(final String tagName,
                                       final Dictionary<V> dictionary,
                                       final NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName, dictionary, noSuchKeyExceptionFactory);
    }

    @Override
    protected final Stream<String> findAliases(final SearchersParsingContext context) {
        final String key = context.getLastContent();
        return Stream.of(key);
    }
}
