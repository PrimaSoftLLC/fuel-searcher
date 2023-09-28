package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public abstract class FuelHeaderMetadata extends PropertyMetadataSource implements Translatable {
    private final Map<String, Integer> fuelOffsetsByValues;

    @Getter
    private final SpecificationPropertyExtractor valueExtractor;

    @Override
    public final String findAlias() {
        return this.findPropertyName();
    }

    public final String[] findValues() {
        return this.fuelOffsetsByValues
                .keySet()
                .toArray(String[]::new);
    }

    public final Optional<Integer> findFuelOffset(final String headerValue) {
        return ofNullable(this.fuelOffsetsByValues.get(headerValue));
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
