package by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

@RequiredArgsConstructor
public abstract class FuelHeaderMetadataFactory {
    private final SpecificationPropertyExtractor propertyExtractor;
    private final String[] headerValues;

    public final FuelHeaderMetadata create() {
        final Map<String, Integer> fuelOffsetsByHeaders = this.createFuelOffsetsByHeaders();
        return this.create(this.propertyExtractor, fuelOffsetsByHeaders);
    }

    protected abstract FuelHeaderMetadata create(final SpecificationPropertyExtractor propertyExtractor,
                                                 final Map<String, Integer> fuelOffsetsByHeaders);

    private Map<String, Integer> createFuelOffsetsByHeaders() {
        return range(0, this.headerValues.length)
                .boxed()
                .collect(
                        toMap(
                                i -> this.headerValues[i],
                                identity()
                        )
                );
    }
}
