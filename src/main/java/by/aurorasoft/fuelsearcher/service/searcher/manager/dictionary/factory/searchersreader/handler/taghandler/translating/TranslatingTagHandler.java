package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.TagHandler;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class TranslatingTagHandler<V extends Translatable> extends TagHandler {
    private final Dictionary<V> dictionary;
    private final NoSuchKeyException.NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory;

    public TranslatingTagHandler(final String tagName,
                                 final Dictionary<V> dictionary,
                                 final NoSuchKeyException.NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName);
        this.dictionary = dictionary;
        this.noSuchKeyExceptionFactory = noSuchKeyExceptionFactory;
    }

    @Override
    public final void handleEndTag(final SearchersParsingContext context) {
        this.accumulateTranslatedValues(context);
        this.accumulateAdditionalValues(context);
    }

    protected abstract Stream<String> findKeys(final SearchersParsingContext context);

    protected abstract void accumulateTranslatedValue(final SearchersParsingContext context, final V value);

    protected abstract void accumulateAdditionalValues(final SearchersParsingContext context);

    private void accumulateTranslatedValues(final SearchersParsingContext context) {
        this.findKeys(context)
                .map(this::findTranslatedValue)
                .forEach(value -> this.accumulateTranslatedValue(context, value));
    }

    private V findTranslatedValue(final String key) {
        final Optional<V> optionalValue = this.dictionary.find(key);
        return optionalValue.orElseThrow(() -> this.noSuchKeyExceptionFactory.apply(key));
    }
}
