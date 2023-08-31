package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.dictionary;

import by.aurorasoft.fuelsearcher.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoutingLengthExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FuelHeaderMetadataDictionary extends Dictionary<FuelHeaderMetadata> {

    public FuelHeaderMetadataDictionary() {
        super(List.of(
                //TODO: read from file or do three subclasses for FuelHeaderMetadata
                new FuelHeaderMetadata(
                        "длина гона",
                        List.of("Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"),
                        new RoutingLengthExtractor()
                ),
                new FuelHeaderMetadata(
                        "норма внесения удобрений",
                        List.of("Менее 30", "30-50", "Более 50"),
                        new SpreadRateExtractor()
                ),
                new FuelHeaderMetadata(
                        "группа дорог",
                        List.of("I", "II", "III"),
                        new RoadGroupExtractor()
                )
        ));
    }

}
