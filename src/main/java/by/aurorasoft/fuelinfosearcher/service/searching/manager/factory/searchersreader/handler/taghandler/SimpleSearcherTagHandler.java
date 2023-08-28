package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class SimpleSearcherTagHandler extends TagHandler {
    private static final String TAG_NAME = "simple-searcher";

    public SimpleSearcherTagHandler() {
        super(TAG_NAME);
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        context.buildSimpleSearcher();
    }

}
