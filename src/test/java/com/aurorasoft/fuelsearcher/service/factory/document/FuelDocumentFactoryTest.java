package com.aurorasoft.fuelsearcher.service.factory.document;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.service.factory.document.corrector.FuelDocumentCorrector;
import com.aurorasoft.fuelsearcher.service.factory.document.loader.FuelDocumentLoader;
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
    private FuelDocumentLoader mockedLoader;

    @Mock
    private FuelDocumentCorrector mockedCorrector;

    private FuelDocumentFactory documentFactory;

    @Before
    public void initializeDocumentFactory() {
        this.documentFactory = new FuelDocumentFactory(this.mockedLoader, this.mockedCorrector);
    }

    @Test
    public void documentShouldBeCreated() {
        final String givenFilePath = "file-path";

        final FuelDocument givenDocument = mock(FuelDocument.class);
        when(this.mockedLoader.load(same(givenFilePath))).thenReturn(givenDocument);

        final FuelDocument actual = this.documentFactory.create(givenFilePath);
        assertSame(givenDocument, actual);

        verify(this.mockedCorrector, times(1)).correct(same(givenDocument));
    }

}
