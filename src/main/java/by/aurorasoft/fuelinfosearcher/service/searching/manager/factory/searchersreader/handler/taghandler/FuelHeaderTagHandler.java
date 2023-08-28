package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import org.springframework.stereotype.Component;

@Component
public final class FuelHeaderTagHandler extends TagHandler {
    private static final String TAG_NAME = "fuel-header";

    public FuelHeaderTagHandler() {
        super(TAG_NAME);
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        context.accumulateFuelHeader();
    }
}
