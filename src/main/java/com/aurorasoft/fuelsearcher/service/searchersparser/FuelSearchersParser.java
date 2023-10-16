package com.aurorasoft.fuelsearcher.service.searchersparser;

import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandler;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParser {
    private final SAXParser saxParser;
    private final SearchersParsingHandlerFactory parsingHandlerFactory;

    public List<FuelSearcher> parse(final String filePath) {
        try {
            final SearchersParsingHandler parsingHandler = this.parsingHandlerFactory.create();
            this.saxParser.parse(filePath, parsingHandler);
            return parsingHandler.findParsedSearchers();
        } catch (final SAXException | IOException cause) {
            throw new ParsingException(cause);
        }
    }

    private static final class ParsingException extends RuntimeException {

        @SuppressWarnings("unused")
        public ParsingException() {

        }

        @SuppressWarnings("unused")
        public ParsingException(final String description) {
            super(description);
        }

        public ParsingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public ParsingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
