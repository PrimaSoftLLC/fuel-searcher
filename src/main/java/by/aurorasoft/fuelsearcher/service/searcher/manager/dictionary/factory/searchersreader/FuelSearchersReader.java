package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.exception.FuelSearchersReadingException;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.SearchersParsingHandler;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.factory.SearchersParsingHandlerFactory;
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
}