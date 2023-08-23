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
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

//TODO: сделать так чтобы все было на уровне тегов
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

    public void nextTag() {
        try {
            this.xmlStreamReader.nextTag();
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

    public String findTagText() {
        try {
            return this.xmlStreamReader.getElementText();
        } catch (final XMLStreamException cause) {
            throw new XMLReadingException(cause);
        }
    }

    public void nextStartTag() {
        while (!this.isCurrentStartTag()) {
            this.nextTag();
        }
    }

    public void skipUntilNextTag() {
        this.nextTag();
    }

    public void skipUntilStartTag(final String tagName) {
        while (!this.isStartTag(tagName)) {
            this.nextTag();
        }
    }

    public boolean isCurrentStartTag() {
        return this.findEventType() == START_ELEMENT;
    }

    //TODO: refactor
    public boolean isStartTag(final String tagName) {
        return this.findEventType() == START_ELEMENT && Objects.equals(this.findLocalName(), tagName);
    }

    //TODO: refactor
    public boolean isEndTag(final String tagName) {
        return this.findEventType() == END_ELEMENT && Objects.equals(this.findLocalName(), tagName);
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
