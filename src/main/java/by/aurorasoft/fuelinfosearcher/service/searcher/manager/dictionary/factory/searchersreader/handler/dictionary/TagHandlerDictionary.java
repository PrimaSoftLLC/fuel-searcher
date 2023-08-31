package by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class TagHandlerDictionary extends Dictionary<TagHandler> {

    public TagHandlerDictionary(final List<TagHandler> handlers) {
        super(handlers);
    }

}
