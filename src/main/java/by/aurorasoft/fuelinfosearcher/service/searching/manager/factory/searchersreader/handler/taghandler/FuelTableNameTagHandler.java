package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import org.springframework.stereotype.Component;

@Component
public final class FuelTableNameTagHandler extends AbstractTagHandler {
    private static final String TAG_NAME = "name";

    private final FuelTableSearcher fuelTableSearcher;

    public FuelTableNameTagHandler(final FuelTableSearcher fuelTableSearcher) {
        super(TAG_NAME);
        this.fuelTableSearcher = fuelTableSearcher;
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        final String tableName = context.getLastContent();
        final FuelTable fuelTable = this.fuelTableSearcher.findFuelTable(tableName);
        context.accumulateFuelTable(fuelTable);
    }

}
