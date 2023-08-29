package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException.NoSuchKeyExceptionFactory;

import java.util.stream.Stream;

public abstract class SimpleTranslatingTagHandler<V> extends TranslatingTagHandler<V> {

    public SimpleTranslatingTagHandler(final String tagName,
                                       final Dictionary<V> dictionary,
                                       final NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName, dictionary, noSuchKeyExceptionFactory);
    }

    @Override
    protected final Stream<String> findKeys(final SearchersParsingContext context) {
        final String key = context.getLastContent();
        return Stream.of(key);
    }
}
