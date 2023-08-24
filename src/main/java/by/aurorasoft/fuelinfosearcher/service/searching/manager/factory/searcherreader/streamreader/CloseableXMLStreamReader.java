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
import static javax.xml.stream.XMLStreamConstants.*;

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

    public int next() {
        try {
            return this.xmlStreamReader.next();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public String getLocalName() {
        return this.xmlStreamReader.getLocalName();
    }

    public boolean isEndDocument() {
        return this.xmlStreamReader.getEventType() == END_DOCUMENT;
    }

    public void nextTag() {
        try {
            this.xmlStreamReader.nextTag();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public String findTagName() {
        return this.xmlStreamReader.getLocalName();
    }

    public String findTagText() {
        try {
            this.xmlStreamReader.next();
            return xmlStreamReader.getText().trim();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public void nextStartTag() {
        while (!this.isCurrentStartTag()) {
            this.nextTag();
        }
    }

    public boolean isCurrentStartTag() {
        return this.xmlStreamReader.getEventType() == START_ELEMENT;
    }

    public boolean isCurrentEndTag() {
        return this.xmlStreamReader.getEventType() == END_ELEMENT;
    }

    public boolean isStartTag(final String tagName) {
        return this.isCurrentStartTag() && Objects.equals(this.findTagName(), tagName);
    }

    public boolean isEndTag(final String tagName) {
        return this.isCurrentEndTag() && Objects.equals(this.findTagName(), tagName);
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
}
