package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public abstract class FuelHeaderMetadata implements Translatable, PropertyMetadataSource {
    private final Map<String, Integer> fuelOffsetsByValues;
    private final SpecificationPropertyExtractor valueExtractor;

    @Override
    public final String findAlias() {
        return this.findPropertyName();
    }

    @Override
    public final String findPropertyName() {
        return this.valueExtractor.getPropertyName();
    }
}
