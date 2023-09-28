package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class PropertyMetadataSource {
    private final SpecificationPropertyExtractor propertyExtractor;

    public final String findPropertyName() {
        return this.propertyExtractor.getPropertyName();
    }
}
