package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesIgnoringWhitespacesAndCase;

@RequiredArgsConstructor
public abstract class PropertyMetadataSearcher<S extends PropertyMetadataSource> {
    private final Class<S> sourceType;

    public final boolean isAbleToFind(final PropertyMetadataSource source) {
        return this.sourceType.isInstance(source);
    }

    public final PropertyMetadata find(final FuelTable fuelTable, final PropertyMetadataSource source) {
        final S concreteSource = this.sourceType.cast(source);
        final String propertyName = source.findPropertyName();
        final String[] uniqueAllowableValues = this.findUniqueAllowableValues(fuelTable, concreteSource);
        return this.createMetadata(propertyName, uniqueAllowableValues);
    }

    protected abstract Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final S source);

    protected abstract boolean isAllowableValuesDuplicated();

    private String[] findUniqueAllowableValues(final FuelTable fuelTable, final S source) {
        final List<IBodyElement> tableElements = fuelTable.elements();
        final Stream<String> allowableValues = this.findAllowableValues(tableElements, source);
        final Stream<String> uniqueAllowableValues = this.removeDuplicatedAllowableValuesIfNecessary(allowableValues);
        return uniqueAllowableValues.toArray(String[]::new);
    }

    private Stream<String> removeDuplicatedAllowableValuesIfNecessary(final Stream<String> allowableValues) {
        return this.isAllowableValuesDuplicated()
                ? removeDuplicatesIgnoringWhitespacesAndCase(allowableValues)
                : allowableValues;
    }

    private PropertyMetadata createMetadata(final String propertyName, final String[] allowableValues) {
        return PropertyMetadata.builder()
                .propertyName(propertyName)
                .allowableValues(allowableValues)
                .build();
    }
}
