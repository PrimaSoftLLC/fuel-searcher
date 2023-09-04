package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.FuelSearcherDictionaryFactory;
import by.aurorasoft.fuelsearcher.service.documentfactory.FuelDocumentFactory;
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
    public void dictionaryShouldBeCreated() {
        final FuelSearcherDictionaryFactory givenFactory = mock(FuelSearcherDictionaryFactory.class);

        final FuelSearcherDictionary givenDictionary = mock(FuelSearcherDictionary.class);
        final String givenFilePath = "file-path";
        when(givenFactory.create(same(givenFilePath))).thenReturn(givenDictionary);

        final FuelSearcherDictionary actual = this.configuration.fuelSearcherDictionary(givenFactory, givenFilePath);
        assertSame(givenDictionary, actual);
    }
}
