package by.aurorasoft.fuelsearcher.service.documentfactory;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.corrector.FuelDocumentCorrector;
import by.aurorasoft.fuelsearcher.service.documentfactory.loader.FuelDocumentLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class FuelDocumentFactoryTest {

    @Mock
    private FuelDocumentLoader mockedDocumentLoader;

    @Mock
    private FuelDocumentCorrector mockedDocumentCorrector;

    private FuelDocumentFactory documentFactory;

    @Before
    public void initializeDocumentFactory() {
        this.documentFactory = new FuelDocumentFactory(this.mockedDocumentLoader, this.mockedDocumentCorrector);
    }

    @Test
    public void documentShouldBeCreated() {
        final String givenFilePath = "file-path";

        final FuelDocument givenDocument = mock(FuelDocument.class);
        when(this.mockedDocumentLoader.load(same(givenFilePath))).thenReturn(givenDocument);

        final FuelDocument actual = this.documentFactory.create(givenFilePath);
        assertSame(givenDocument, actual);

        verify(this.mockedDocumentCorrector, times(1)).correct(same(givenDocument));
    }

}
