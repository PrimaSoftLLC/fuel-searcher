package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader;

import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.exception.FuelSearchersReadingException;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.factory.FuelSearchersParsingHandlerFactory;
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
    private final FuelSearchersParsingHandlerFactory parsingHandlerFactory;

    public List<FuelSearcher> read(final String filePath) {
        try {
            final FuelSearchersParsingHandler parsingHandler = this.parsingHandlerFactory.create();
            this.saxParser.parse(filePath, parsingHandler);
            return parsingHandler.findParsedSearchers();
        } catch (final SAXException | IOException cause) {
            throw new FuelSearchersReadingException(cause);
        }
    }
}
