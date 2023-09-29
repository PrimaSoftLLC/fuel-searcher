package by.aurorasoft.fuelsearcher.configuration;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SAXParserConfigurationTest {
    private final SAXParserConfiguration configuration = new SAXParserConfiguration();

    @Test
    public void parserFactoryShouldBeCreated() {
        final SAXParserFactory actual = this.configuration.saxParserFactory();
        assertNotNull(actual);
    }

    @Test
    public void parserShouldBeCreated()
            throws Exception {
        final SAXParserFactory givenFactory = mock(SAXParserFactory.class);
        final SAXParser givenParser = mock(SAXParser.class);
        when(givenFactory.newSAXParser()).thenReturn(givenParser);

        final SAXParser actual = this.configuration.saxParser(givenFactory);
        assertSame(givenParser, actual);
    }
}
