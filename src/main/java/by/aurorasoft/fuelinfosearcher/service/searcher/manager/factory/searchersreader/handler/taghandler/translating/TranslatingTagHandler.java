package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class TranslatingTagHandler<V> extends TagHandler {
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
