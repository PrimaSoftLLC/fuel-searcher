package by.aurorasoft.fuelsearcher.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static javax.xml.parsers.SAXParserFactory.newInstance;

@Configuration
public class SAXParserConfiguration {

    @Bean
    public SAXParserFactory saxParserFactory() {
        return newInstance();
    }

    @Bean
    public SAXParser saxParser(final SAXParserFactory factory)
            throws ParserConfigurationException, SAXException {
        return factory.newSAXParser();
    }
}
