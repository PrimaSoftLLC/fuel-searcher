package by.aurorasoft.fuelinfosearcher.model;

import by.aurorasoft.fuelinfosearcher.dictionary.Translatable;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
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
