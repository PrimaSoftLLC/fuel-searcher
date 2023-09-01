package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public abstract class FuelHeaderMetadata implements Translatable {
    private final String[] values;
    private final SpecificationPropertyExtractor fuelHeaderExtractor;

    @Override
    public String findAlias() {
        return this.fuelHeaderExtractor.findAlias();
    }
}
