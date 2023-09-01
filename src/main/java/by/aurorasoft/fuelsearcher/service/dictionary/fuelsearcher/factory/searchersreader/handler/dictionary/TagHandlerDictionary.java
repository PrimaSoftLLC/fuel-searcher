package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class TagHandlerDictionary extends Dictionary<TagHandler> {

    public TagHandlerDictionary(final List<TagHandler> handlers) {
        super(handlers);
    }

}
