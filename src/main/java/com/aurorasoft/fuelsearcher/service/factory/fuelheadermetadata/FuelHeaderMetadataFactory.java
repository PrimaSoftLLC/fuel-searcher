package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

@RequiredArgsConstructor
public abstract class FuelHeaderMetadataFactory<E extends SpecificationPropertyExtractor, M extends FuelHeaderMetadata> {
    private final E propertyExtractor;

    public final M create(final String[] headerValues) {
        final Map<String, Integer> fuelOffsetsByHeaders = createFuelOffsetsByHeaders(headerValues);
        return this.create(this.propertyExtractor, fuelOffsetsByHeaders);
    }

    protected abstract M create(final E propertyExtractor, final Map<String, Integer> fuelOffsetsByHeaders);

    private static Map<String, Integer> createFuelOffsetsByHeaders(final String[] headerValues) {
        return range(0, headerValues.length)
                .boxed()
                .collect(
                        toMap(
                                i -> headerValues[i],
                                identity(),
                                (existing, replacement) -> throwDuplicatedHeaderValueException(headerValues),
                                LinkedHashMap::new
                        )
                );
    }

    private static Integer throwDuplicatedHeaderValueException(final String[] headerValues) {
        final String headerValuesAsString = Arrays.toString(headerValues);
        throw new IllegalArgumentException(
                "Duplicated fuel-header's value. Given values: %s".formatted(headerValuesAsString)
        );
    }
}
