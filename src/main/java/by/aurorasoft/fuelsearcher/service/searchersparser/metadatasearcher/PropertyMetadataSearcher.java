package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@RequiredArgsConstructor
public abstract class PropertyMetadataSearcher<S> {

    @Getter
    private final Class<S> sourceType;

    public final PropertyMetadata find(final FuelTable fuelTable, final Object source) {
        final List<IBodyElement> tableElements = fuelTable.elements();
        final S concreteSource = this.sourceType.cast(source);
        return this.findByConcreteSource(tableElements, concreteSource);
    }

    protected abstract PropertyMetadata findByConcreteSource(final List<IBodyElement> tableElements, final S source);

}
