package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class SpecificationPropertyExtractorDictionary extends Dictionary<SpecificationPropertyExtractor> {

    //TODO: read from file
    private static final Map<String, SpecificationPropertyExtractor> EXTRACTORS_BY_KEYS = ofEntries(
            entry("длина гона", FuelSpecificationExtractingPropertyUtil::extractRoutingLength),
            entry("механизм", FuelSpecificationExtractingPropertyUtil::extractMachinery),
            entry("трактор", FuelSpecificationExtractingPropertyUtil::extractTractor),
            entry("норма внесения удобрений", FuelSpecificationExtractingPropertyUtil::extractSpreadRate),
            entry("комбайн", FuelSpecificationExtractingPropertyUtil::extractCombine),
            entry("соотношение массы зерна к массе соломы", FuelSpecificationExtractingPropertyUtil::extractWeightRatioGrainToStraw)
    );

    public SpecificationPropertyExtractorDictionary() {
        super(EXTRACTORS_BY_KEYS);
    }
}
