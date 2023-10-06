package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.metadata.TablePropertyMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.removeDuplicatesConsideringOnlyLettersAndDigits;

@RequiredArgsConstructor
public abstract class TablePropertyMetadataSearcher<S extends PropertyMetadataSource> {
    private final Class<S> sourceType;

    public final boolean isAbleToFind(final PropertyMetadataSource source) {
        return this.sourceType.isInstance(source);
    }

    public final TablePropertyMetadata find(final FuelTable table, final PropertyMetadataSource source) {
        final String tableName = table.name();
        final String propertyName = source.findPropertyName();
        final String[] uniqueAllowableValues = this.findUniqueAllowableValues(table, source);
        return new TablePropertyMetadata(tableName, propertyName, uniqueAllowableValues);
    }

    protected abstract Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final S source);

    protected abstract boolean isAllowableValuesDuplicated();

    private String[] findUniqueAllowableValues(final FuelTable table, final PropertyMetadataSource source) {
        final S concreteSource = this.sourceType.cast(source);
        return this.findUniqueAllowableValuesByConcreteSource(table, concreteSource);
    }

    private String[] findUniqueAllowableValuesByConcreteSource(final FuelTable table, final S source) {
        final List<IBodyElement> tableElements = table.elements();
        final Stream<String> allowableValues = this.findAllowableValues(tableElements, source);
        final Stream<String> uniqueAllowableValues = this.removeDuplicatedAllowableValuesIfNecessary(allowableValues);
        return uniqueAllowableValues.toArray(String[]::new);
    }

    private Stream<String> removeDuplicatedAllowableValuesIfNecessary(final Stream<String> allowableValues) {
        return this.isAllowableValuesDuplicated()
                ? removeDuplicatesConsideringOnlyLettersAndDigits(allowableValues)
                : allowableValues;
    }
}
