package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.functionalinterface.FuelSpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class FuelSpecificationPropertyExtractorDictionary
        extends AbstractDictionary<FuelSpecificationPropertyExtractor> {
    private static final Map<String, FuelSpecificationPropertyExtractor> EXTRACTORS_BY_PROPERTY_NAMES = ofEntries(
            entry("длина гона", FuelSpecificationExtractingPropertyUtil::extractRoutingLength)
    );

    public FuelSpecificationPropertyExtractorDictionary() {
        super(EXTRACTORS_BY_PROPERTY_NAMES);
    }
}
