package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.dictionary.FuelTableDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import org.springframework.stereotype.Component;

@Component
public final class TableNameTagHandler extends SimpleTranslatingTagHandler<FuelTable> {
    private static final String TAG_NAME = "table-name";

    public TableNameTagHandler(final FuelTableDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchFuelTableException::new);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected void accumulateTranslatedValue(final SearchersParsingContext context, final FuelTable fuelTable) {
        context.accumulateFuelTable(fuelTable);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }

    private static final class NoSuchFuelTableException extends NoSuchKeyException {

        @SuppressWarnings("unused")
        public NoSuchFuelTableException() {

        }

        public NoSuchFuelTableException(final String key) {
            super(key);
        }

        @SuppressWarnings("unused")
        public NoSuchFuelTableException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuchFuelTableException(final String key, final Exception cause) {
            super(key, cause);
        }

    }

}
