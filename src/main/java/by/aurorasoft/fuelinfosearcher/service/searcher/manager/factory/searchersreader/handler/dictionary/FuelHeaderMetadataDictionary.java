package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class FuelHeaderMetadataDictionary extends Dictionary<FuelHeaderMetadata> {

    //TODO: read from file
    private static final Map<String, FuelHeaderMetadata> METADATA_BY_HEADER_NAMES = ofEntries(
            entry(
                    "длина гона",
                    new FuelHeaderMetadata(
                            "длина гона",
                            List.of("Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"),
                            FuelSpecificationExtractingPropertyUtil::extractRoutingLength
                    )
            ),
            entry(
                    "норма внесения удобрений",
                    new FuelHeaderMetadata(
                            "норма внесения удобрений",
                            List.of("Менее 30", "30-50", "Более 50"),
                            FuelSpecificationExtractingPropertyUtil::extractSpreadRate
                    )
            ),
            entry(
                    "группа дорог",
                    new FuelHeaderMetadata(
                            "группа дорог",
                            List.of("I", "II", "III"),
                            FuelSpecificationExtractingPropertyUtil::extractRoadGroup
                    )
            )
    );

    public FuelHeaderMetadataDictionary() {
        super(METADATA_BY_HEADER_NAMES);
    }

}
