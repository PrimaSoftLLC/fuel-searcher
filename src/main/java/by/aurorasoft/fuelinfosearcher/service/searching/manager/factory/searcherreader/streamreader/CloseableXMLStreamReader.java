package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.exception.XMLReadingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import static javax.xml.stream.XMLInputFactory.newFactory;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public final class CloseableXMLStreamReader implements Closeable {
    private final XMLStreamReader xmlStreamReader;

    public CloseableXMLStreamReader(final String filePath) {
        this.xmlStreamReader = createXMLStreamReader(filePath);
    }

    public boolean hasNext() {
        try {
            return this.xmlStreamReader.hasNext();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public void next() {
        try {
            this.xmlStreamReader.next();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public int findEventType() {
        return this.xmlStreamReader.getEventType();
    }

    public String findLocalName() {
        return this.xmlStreamReader.getLocalName();
    }

    public String findElementText() {
        try {
            return this.xmlStreamReader.getElementText();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public void skipUntilNextTagWithGivenName(final String tagName) {
        while (!this.isCurrentStartTagWithGivenName(tagName)) {
            this.next();
        }
    }



    @Override
    public void close() {
        try {
            this.xmlStreamReader.close();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    private static XMLStreamReader createXMLStreamReader(final String filePath) {
        try {
            final XMLInputFactory inputFactory = newFactory();
            final InputStream inputStream = new FileInputStream(filePath);
            return inputFactory.createXMLStreamReader(inputStream);
        } catch (final FileNotFoundException | XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    private boolean isCurrentStartTagWithGivenName(final String elementName) {
        return this.findEventType() == START_ELEMENT && Objects.equals(this.findLocalName(), elementName);
    }
}
