package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import com.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.TagHandler;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class TranslatingTagHandler<V extends Translatable> extends TagHandler {
    private final Dictionary<V> dictionary;
    private final NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory;

    public TranslatingTagHandler(final String tagName,
                                 final Dictionary<V> dictionary,
                                 final NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName);
        this.dictionary = dictionary;
        this.noSuchKeyExceptionFactory = noSuchKeyExceptionFactory;
    }

    @Override
    public final void handleEndTag(final SearchersParsingContext context) {
        this.accumulateTranslatedValues(context);
        this.accumulateAdditionalValues(context);
    }

    protected abstract Stream<String> findAliases(final SearchersParsingContext context);

    protected abstract void accumulateTranslatedValue(final SearchersParsingContext context, final V value);

    protected abstract void accumulateAdditionalValues(final SearchersParsingContext context);

    private void accumulateTranslatedValues(final SearchersParsingContext context) {
        this.findAliases(context)
                .map(this::findTranslatedValue)
                .forEach(value -> this.accumulateTranslatedValue(context, value));
    }

    private V findTranslatedValue(final String alias) {
        final Optional<V> optionalValue = this.dictionary.find(alias);
        return optionalValue.orElseThrow(() -> this.noSuchKeyExceptionFactory.create(alias));
    }
}
