package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class FuelHeaderMetadata implements Translatable {
    private final String[] values;
    private final SpecificationPropertyExtractor fuelHeaderExtractor;

    @Override
    public final String findAlias() {
        return this.fuelHeaderExtractor.findAlias();
    }
}
