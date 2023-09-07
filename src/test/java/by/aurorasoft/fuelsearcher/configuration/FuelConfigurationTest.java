package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelConfigurationTest {
    private final FuelConfiguration configuration = new FuelConfiguration();

    @Test
    public void documentShouldBeCreated() {
        final FuelDocumentFactory givenFactory = mock(FuelDocumentFactory.class);

        final FuelDocument givenFuelDocument = mock(FuelDocument.class);
        final String givenFilePath = "file-path";
        when(givenFactory.create(same(givenFilePath))).thenReturn(givenFuelDocument);

        final FuelDocument actual = this.configuration.document(givenFactory, givenFilePath);
        assertSame(givenFuelDocument, actual);
    }

    @Test
    public void searchersParsingResultShouldBeCreated() {
        final FuelSearchersParser givenParser = mock(FuelSearchersParser.class);

        final SearchersParsingResult givenParsingResult = mock(SearchersParsingResult.class);
        final String givenFilePath = "file-path";
        when(givenParser.parse(same(givenFilePath))).thenReturn(givenParsingResult);

        final SearchersParsingResult actual = this.configuration.searchersParsingResult(givenParser, givenFilePath);
        assertSame(givenParsingResult, actual);
    }
}
