package by.aurorasoft.fuelinfosearcher;

import by.aurorasoft.fuelinfosearcher.configuration.MainConfiguration;
import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public final class Runner {
    public static void main(final String... args) throws Exception {
        final ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);

        final FuelSearchersParsingHandler parser = new FuelSearchersParsingHandler(
                context.getBean(FuelDocument.class),
                context.getBean(GroupRowFilterDictionary.class),
                context.getBean(IntermediateRowFilterFactoryDictionary.class),
                context.getBean(ConclusiveRowFilterFactoryDictionary.class),
                context.getBean(SpecificationPropertyExtractorDictionary.class)
        );

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        saxParser.parse(new File("fuel-tables.xml"), parser);

        List<SimpleTableFuelSearcher> searchers = parser.getSearchers();
        System.out.println(searchers);
    }
}
