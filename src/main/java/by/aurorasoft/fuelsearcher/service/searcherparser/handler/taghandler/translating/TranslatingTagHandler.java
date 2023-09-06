package by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcherparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.TagHandler;
import by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

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
        return optionalValue.orElseThrow(() -> this.noSuchKeyExceptionFactory.apply(alias));
    }
}
