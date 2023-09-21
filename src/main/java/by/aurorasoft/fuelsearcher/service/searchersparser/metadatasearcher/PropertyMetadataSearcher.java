package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesIgnoringWhitespacesAndCase;

@RequiredArgsConstructor
public abstract class PropertyMetadataSearcher<S> {
    private final Class<S> sourceType;

    public final boolean isAbleToFind(final Object source) {
        return this.sourceType.isInstance(source);
    }

    public final PropertyMetadata find(final FuelTable fuelTable, final Object source) {
        final S concreteSource = this.sourceType.cast(source);
        final String propertyName = this.findPropertyName(concreteSource);
        final String[] uniqueAllowableValues = this.findUniqueAllowableValues(fuelTable, concreteSource);
        return this.createMetadata(propertyName, uniqueAllowableValues);
    }

    protected abstract String findPropertyName(final S source);

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
