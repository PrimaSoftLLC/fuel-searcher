package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class SimpleSearcherTagHandler extends TagHandler {

    public SimpleSearcherTagHandler(final String tagName) {
        super(tagName);
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        context.buildSimpleSearcher();
    }

}
