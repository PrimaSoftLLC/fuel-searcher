package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser;

import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler.SearchersParsingHandler;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.handler.factory.SearchersParsingHandlerFactory;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.result.SearchersParsingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParser {
    private final SAXParser saxParser;
    private final SearchersParsingHandlerFactory parsingHandlerFactory;

    public SearchersParsingResult read(final String filePath) {
        try {
            final SearchersParsingHandler parsingHandler = this.parsingHandlerFactory.create();
            this.saxParser.parse(filePath, parsingHandler);
            return parsingHandler.findParsingResult();
        } catch (final SAXException | IOException cause) {
            throw new FuelSearchersParsingException(cause);
        }
    }

    private static final class FuelSearchersParsingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FuelSearchersParsingException() {

        }

        @SuppressWarnings("unused")
        public FuelSearchersParsingException(final String description) {
            super(description);
        }

        public FuelSearchersParsingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FuelSearchersParsingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
