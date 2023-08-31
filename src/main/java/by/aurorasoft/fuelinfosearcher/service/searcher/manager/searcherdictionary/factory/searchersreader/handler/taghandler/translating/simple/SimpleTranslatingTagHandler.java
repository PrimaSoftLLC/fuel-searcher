package by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.simple;

import by.aurorasoft.fuelinfosearcher.dictionary.Translatable;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.TranslatingTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

import java.util.stream.Stream;

public abstract class SimpleTranslatingTagHandler<V extends Translatable> extends TranslatingTagHandler<V> {

    public SimpleTranslatingTagHandler(final String tagName,
                                       final Dictionary<V> dictionary,
                                       final NoSuchKeyException.NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName, dictionary, noSuchKeyExceptionFactory);
    }

    @Override
    protected final Stream<String> findKeys(final SearchersParsingContext context) {
        final String key = context.getLastContent();
        return Stream.of(key);
    }
}
