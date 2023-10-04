package com.aurorasoft.fuelsearcher.service.searchersparser.handler;

import com.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.TagHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.xml.sax.Attributes;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class SearchersParsingHandlerTest {

    @Mock
    private TagHandlerDictionary mockedHandlerDictionary;

    @Mock
    private SearchersParsingContext mockedContext;

    private SearchersParsingHandler parsingHandler;

    @Before
    public void initializeParsingHandler() {
        this.parsingHandler = new SearchersParsingHandler(this.mockedHandlerDictionary, this.mockedContext);
    }

    @Test
    public void startElementShouldBeHandled() {
        final String givenUri = "uri";
        final String givenLocalName = "local-name";
        final String givenQualifiedName = "qualified-name";
        final Attributes givenAttributes = mock(Attributes.class);

        final TagHandler givenTagHandler = mock(TagHandler.class);
        when(this.mockedHandlerDictionary.find(same(givenQualifiedName))).thenReturn(Optional.of(givenTagHandler));

        this.parsingHandler.startElement(givenUri, givenLocalName, givenQualifiedName, givenAttributes);

        verify(this.mockedContext, times(1)).setLastAttributes(same(givenAttributes));
        verify(givenTagHandler, times(1)).handleStartTag(same(this.mockedContext));
    }

    @Test
    public void startElementShouldNotBeHandled() {
        final String givenUri = "uri";
        final String givenLocalName = "local-name";
        final String givenQualifiedName = "qualified-name";
        final Attributes givenAttributes = mock(Attributes.class);

        when(this.mockedHandlerDictionary.find(same(givenQualifiedName))).thenReturn(empty());

        this.parsingHandler.startElement(givenUri, givenLocalName, givenQualifiedName, givenAttributes);

        verify(this.mockedContext, times(1)).setLastAttributes(same(givenAttributes));
    }

    @Test
    public void endElementShouldBeHandled() {
        final String givenUri = "uri";
        final String givenLocalName = "local-name";
        final String givenQualifiedName = "qualified-name";

        final TagHandler givenTagHandler = mock(TagHandler.class);
        when(this.mockedHandlerDictionary.find(same(givenQualifiedName))).thenReturn(Optional.of(givenTagHandler));

        this.parsingHandler.endElement(givenUri, givenLocalName, givenQualifiedName);

        verify(givenTagHandler, times(1)).handleEndTag(same(this.mockedContext));
    }

    @Test
    public void endElementShouldNotBeHandled() {
        final String givenUri = "uri";
        final String givenLocalName = "local-name";
        final String givenQualifiedName = "qualified-name";

        when(this.mockedHandlerDictionary.find(same(givenQualifiedName))).thenReturn(empty());

        this.parsingHandler.endElement(givenUri, givenLocalName, givenQualifiedName);
    }

    @Test
    public void charactersShouldBeHandled() {
        final char[] givenChars = {' ', 'c', 'o', 'n', 't', 'e', 'n', 't', ' ', 't', 't'};
        final int givenStart = 0;
        final int givenLength = 9;

        this.parsingHandler.characters(givenChars, givenStart, givenLength);

        final String expectedLastContent = "content";
        verify(this.mockedContext, times(1)).setLastContent(eq(expectedLastContent));
    }
}
