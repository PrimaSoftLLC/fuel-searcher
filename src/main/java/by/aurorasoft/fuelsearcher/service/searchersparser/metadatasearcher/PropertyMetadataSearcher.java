package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

//TODO: test
@RequiredArgsConstructor
public abstract class PropertyMetadataSearcher<S> {

    @Getter
    private final Class<S> sourceType;

    public final PropertyMetadata find(final FuelTable fuelTable, final Object source) {
        final S concreteSource = this.sourceType.cast(source);
        final String propertyName = this.findPropertyName(concreteSource);
        final String[] allowableValues = this.findAllowableValues(fuelTable, concreteSource);
        return this.createMetadata(propertyName, allowableValues);
    }

    protected abstract String findPropertyName(final S source);

    protected abstract String[] findAllowableValues(final List<IBodyElement> tableElements, final S source);

    private String[] findAllowableValues(final FuelTable fuelTable, final S source) {
        final List<IBodyElement> tableElements = fuelTable.elements();
        return this.findAllowableValues(tableElements, source);
    }

    private PropertyMetadata createMetadata(final String propertyName, final String[] allowableValues) {
        return PropertyMetadata.builder()
                .propertyName(propertyName)
                .allowableValues(allowableValues)
                .build();
    }
}
