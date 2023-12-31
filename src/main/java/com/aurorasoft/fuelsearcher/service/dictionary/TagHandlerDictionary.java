package com.aurorasoft.fuelsearcher.service.dictionary;

import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.TagHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class TagHandlerDictionary extends Dictionary<TagHandler> {

    public TagHandlerDictionary(final List<TagHandler> handlers) {
        super(handlers);
    }

}
