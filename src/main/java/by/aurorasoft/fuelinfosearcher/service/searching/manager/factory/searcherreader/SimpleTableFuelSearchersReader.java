package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;

import java.util.ArrayList;
import java.util.List;

public final class SimpleTableFuelSearchersReader extends AbstractTableFuelSearchersReader<SimpleTableFuelSearcher> {
    private static final String TAG_NAME_SEARCHERS = "simple-fuel-tables";
    private static final String TAG_NAME_TABLE_NAME = "name";
    private static final String TAG_NAME_FUEL_HEADERS = "fuel-headers";
    private static final String TAG_NAME_FUEL_HEADER = "fuel-header";

    public SimpleTableFuelSearchersReader() {
        super(TAG_NAME_SEARCHERS);
    }

    @Override
    protected SimpleTableFuelSearcher readSearcher(final CloseableXMLStreamReader streamReader) {
        streamReader.nextStartTag();   //to <name>
    }

    private static String readTableName(final CloseableXMLStreamReader streamReader) {
        final String tableName = streamReader.findTagText();
        streamReader.nextStartTag();   //to <fuel-headers>
        return tableName;
    }

    //after this method 'streamReader' will point to <fuel-header-cell-property>
    private static String[] readFuelHeaders(final CloseableXMLStreamReader streamReader) {
        final List<String> fuelHeaders = new ArrayList<>();
        streamReader.nextStartTag();   //to <fuel-header>
        while (streamReader.isStartTag(TAG_NAME_FUEL_HEADER)) {
            final String currentFuelHeader = streamReader.findTagText();
            fuelHeaders.add(currentFuelHeader);
            streamReader.nextStartTag();
        }
        return fuelHeaders.toArray(String[]::new);
    }

    private static String readFuelHeaderCellProperty(final CloseableXMLStreamReader streamReader) {
        final String fuelHeaderCellProperty = streamReader.findTagText();
        streamReader.nextStartTag();   //to <filter-chain>
        return fuelHeaderCellProperty;
    }


}
