package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.RoadGroupExtractor;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.RoutingLengthExtractor;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpreadRateExtractor;
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
