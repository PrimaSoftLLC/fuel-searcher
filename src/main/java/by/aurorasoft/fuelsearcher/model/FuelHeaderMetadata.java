package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class FuelHeaderMetadata implements Translatable {
    String name;
    List<String> values;
    SpecificationPropertyExtractor fuelHeaderExtractor;

    @Override
    public String findAlias() {
        return this.name;
    }
}
