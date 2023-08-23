package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;

public final class SimpleTableFuelSearchersReader extends AbstractTableFuelSearchersReader<SimpleTableFuelSearcher> {
    private static final String TAG_NAME_SEARCHERS = "simple-fuel-tables";
    private static final String TAG_NAME_FUEL_HEADERS = "fuel-headers";

    private static final String TAG_NAME_TABLE_NAME = "name";

    public SimpleTableFuelSearchersReader() {
        super(TAG_NAME_SEARCHERS);
    }

    @Override
    protected SimpleTableFuelSearcher readSearcher(final CloseableXMLStreamReader streamReader) {

    }

    private static String readTableName(final CloseableXMLStreamReader streamReader) {
        streamReader.skipUntilNextTagWithGivenName(TAG_NAME_TABLE_NAME);
        return streamReader.findElementText();
    }

    private static String[] readFuelHeaders(final CloseableXMLStreamReader streamReader) {
        streamReader.skipUntilNextTagWithGivenName(TAG_NAME_FUEL_HEADERS);

    }


}
