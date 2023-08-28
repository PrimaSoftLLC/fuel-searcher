package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO: RowFilter rename to Filter everywhere
@RequiredArgsConstructor
@Getter
public abstract class TagHandler {
    private final String tagName;

    public abstract void handle(final FuelSearchersParsingContext context);
}
