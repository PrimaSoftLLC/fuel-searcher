package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader;

import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.SearchersParsingHandler;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersreader.handler.factory.SearchersParsingHandlerFactory;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearchersReader {
    private final SAXParser saxParser;
    private final SearchersParsingHandlerFactory parsingHandlerFactory;

    public List<FuelSearcher> read(final String filePath) {
        try {
            final SearchersParsingHandler parsingHandler = this.parsingHandlerFactory.create();
            this.saxParser.parse(filePath, parsingHandler);
            return parsingHandler.findParsedSearchers();
        } catch (final SAXException | IOException cause) {
            throw new FuelSearchersReadingException(cause);
        }
    }

    private static final class FuelSearchersReadingException extends RuntimeException {

        public FuelSearchersReadingException() {

        }

        public FuelSearchersReadingException(final String description) {
            super(description);
        }

        public FuelSearchersReadingException(final Exception cause) {
            super(cause);
        }

        public FuelSearchersReadingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
