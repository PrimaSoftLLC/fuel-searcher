package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.TagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class TranslatingTagHandler<V> extends TagHandler {
    private final Dictionary<V> dictionary;
    private final Supplier<? extends NoSuchKeyException> noSuchKeyExceptionSupplier;

    public TranslatingTagHandler(final String tagName,
                                 final Dictionary<V> dictionary,
                                 final Supplier<? extends NoSuchKeyException> noSuchKeyExceptionSupplier) {
        super(tagName);
        this.dictionary = dictionary;
        this.noSuchKeyExceptionSupplier = noSuchKeyExceptionSupplier;
    }

    @Override
    public final void handleEndTag(final SearchersParsingContext context) {
        final V value = this.findValue(context);
        this.handleValue(context, value);
    }

    protected abstract void handleValue(final SearchersParsingContext context, final V value);

    private V findValue(final SearchersParsingContext context) {
        final String key = context.getLastContent();
        final Optional<V> optionalValue = this.dictionary.find(key);
        //TODO: add description of exception with 'key'
        return optionalValue.orElseThrow(this.noSuchKeyExceptionSupplier);
    }
}
