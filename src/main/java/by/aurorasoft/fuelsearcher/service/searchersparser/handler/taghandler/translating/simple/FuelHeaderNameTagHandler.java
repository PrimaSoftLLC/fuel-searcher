package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.service.dictionary.FuelHeaderMetadataDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import org.springframework.stereotype.Component;

@Component
public final class FuelHeaderNameTagHandler extends SimpleTranslatingTagHandler<FuelHeaderMetadata> {
    private static final String TAG_NAME = "fuel-header-name";

    public FuelHeaderNameTagHandler(final FuelHeaderMetadataDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchFuelHeaderNameException::new);
    }


    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected void accumulateTranslatedValue(final SearchersParsingContext context,
                                             final FuelHeaderMetadata headerMetadata) {
        context.accumulateFuelHeaderMetadata(headerMetadata);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {

    }

    private static final class NoSuchFuelHeaderNameException extends NoSuchKeyException {

        @SuppressWarnings("unused")
        public NoSuchFuelHeaderNameException() {

        }

        public NoSuchFuelHeaderNameException(final String key) {
            super(key);
        }

        @SuppressWarnings("unused")
        public NoSuchFuelHeaderNameException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuchFuelHeaderNameException(final String key, final Exception cause) {
            super(key, cause);
        }

    }
}
