package by.aurorasoft.fuelsearcher.service.searcherparser;

import by.aurorasoft.fuelsearcher.service.searcherparser.handler.SearchersParsingHandlerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.xml.parsers.SAXParser;

@RunWith(MockitoJUnitRunner.class)
public final class FuelSearcherParserTest {

    @Mock
    private SAXParser mockedSAXParser;

    @Mock
    private SearchersParsingHandlerFactory mockedParsingHandlerFactory;

    private FuelSearchersParser searchersParser;

    @Before
    public void initializeSearchersParser() {
        this.searchersParser = new FuelSearchersParser(this.mockedSAXParser, this.mockedParsingHandlerFactory);
    }

    @Test
    public void

}
