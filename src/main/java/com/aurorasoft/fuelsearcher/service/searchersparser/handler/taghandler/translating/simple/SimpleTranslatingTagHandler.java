package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import com.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.TranslatingTagHandler;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

import java.util.stream.Stream;

public abstract class SimpleTranslatingTagHandler<V extends Translatable> extends TranslatingTagHandler<V> {

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
