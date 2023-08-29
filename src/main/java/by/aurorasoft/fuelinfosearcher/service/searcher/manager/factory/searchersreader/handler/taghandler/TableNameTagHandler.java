package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import org.springframework.stereotype.Component;

@Component
public final class TableNameTagHandler extends TagHandler {
    private static final String TAG_NAME = "table-name";

    private final FuelTableSearcher fuelTableSearcher;

    public TableNameTagHandler(final FuelTableSearcher fuelTableSearcher) {
        super(TAG_NAME);
        this.fuelTableSearcher = fuelTableSearcher;
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        final String tableName = context.getLastContent();
        final FuelTable fuelTable = this.fuelTableSearcher.findFuelTable(tableName);
        context.accumulateFuelTable(fuelTable);
    }

}
