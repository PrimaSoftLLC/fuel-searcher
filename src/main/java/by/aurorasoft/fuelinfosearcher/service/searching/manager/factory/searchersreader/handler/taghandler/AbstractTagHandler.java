package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import lombok.RequiredArgsConstructor;

//TODO: RowFilter rename to Filter everywhere
@RequiredArgsConstructor
public abstract class AbstractTagHandler {
    private final String tagName;

    public abstract void handle(final FuelSearchersParsingContext context);
}
