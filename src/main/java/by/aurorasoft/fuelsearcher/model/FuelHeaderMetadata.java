package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Value;

import java.util.List;

@Value
public class FuelHeaderMetadata implements Translatable {
    String name;
    List<String> values;
    SpecificationPropertyExtractor fuelHeaderExtractor;

    @Override
    public String findAlias() {
        return this.name;
    }
}
