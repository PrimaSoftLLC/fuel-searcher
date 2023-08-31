package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import org.springframework.stereotype.Component;

//TODO: do as dictionary tag handler and remove FuelTableSearcher
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
