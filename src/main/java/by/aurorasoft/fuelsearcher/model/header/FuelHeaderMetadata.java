package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public abstract class FuelHeaderMetadata extends PropertyMetadataSource implements Translatable {
    private final Map<String, Integer> fuelOffsetsByValues;
    private final SpecificationPropertyExtractor valueExtractor;

    @Override
    public final String findAlias() {
        return this.findPropertyName();
    }

    /*
    private static Map<String, Integer> createFuelOffsetsByHeaders(final FuelHeaderMetadata metadata) {
            final String[] values = metadata.get();
            return range(0, values.length)
                    .boxed()
                    .collect(
                            toMap(
                                    i -> values[i],
                                    identity()
                            )
                    );
        }
     */
}
