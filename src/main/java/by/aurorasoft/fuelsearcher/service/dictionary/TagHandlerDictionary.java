package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class TagHandlerDictionary extends Dictionary<TagHandler> {

    public TagHandlerDictionary(final List<TagHandler> handlers) {
        super(handlers);
    }

}
