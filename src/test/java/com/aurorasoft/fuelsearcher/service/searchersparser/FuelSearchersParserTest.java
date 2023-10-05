package com.aurorasoft.fuelsearcher.service.searchersparser;

import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandler;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandlerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class FuelSearchersParserTest {

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
    public void searchersShouldBeParsed()
            throws Exception {
        final String givenFilePath = "filePath";

        final SearchersParsingHandler givenParsingHandler = mock(SearchersParsingHandler.class);
        when(this.mockedParsingHandlerFactory.create()).thenReturn(givenParsingHandler);

        final List<FuelSearcher> givenParsedSearchers = List.of(
                mock(FuelSearcher.class),
                mock(FuelSearcher.class),
                mock(FuelSearcher.class)
        );
        when(givenParsingHandler.findParsedSearchers()).thenReturn(givenParsedSearchers);

        final List<FuelSearcher> actual = this.searchersParser.parse(givenFilePath);
        assertSame(givenParsedSearchers, actual);

        verify(this.mockedSAXParser, times(1)).parse(
                same(givenFilePath),
                same(givenParsingHandler)
        );
    }

    @Test(expected = Exception.class)
    public void searchersShouldNotBeParsedBecauseOfSAXException()
            throws Exception {
        final String givenFilePath = "filePath";

        final SearchersParsingHandler givenParsingHandler = mock(SearchersParsingHandler.class);
        when(this.mockedParsingHandlerFactory.create()).thenReturn(givenParsingHandler);

        doThrow(SAXException.class).when(this.mockedSAXParser).parse(same(givenFilePath), same(givenParsingHandler));

        this.searchersParser.parse(givenFilePath);

        verify(this.mockedSAXParser, times(1)).parse(
                same(givenFilePath),
                same(givenParsingHandler)
        );
    }

    @Test(expected = Exception.class)
    public void searchersShouldNotBeParsedBecauseOfIOException()
            throws Exception {
        final String givenFilePath = "filePath";

        final SearchersParsingHandler givenParsingHandler = mock(SearchersParsingHandler.class);
        when(this.mockedParsingHandlerFactory.create()).thenReturn(givenParsingHandler);

        doThrow(IOException.class).when(this.mockedSAXParser).parse(same(givenFilePath), same(givenParsingHandler));

        this.searchersParser.parse(givenFilePath);

        verify(this.mockedSAXParser, times(1)).parse(
                same(givenFilePath),
                same(givenParsingHandler)
        );
    }

}
