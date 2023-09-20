package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.fuelheader;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.PropertyMetadataSearcher;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

public abstract class FuelHeaderPropertyMetadataSearcher<M extends FuelHeaderMetadata>
        extends PropertyMetadataSearcher<M> {

    public FuelHeaderPropertyMetadataSearcher(final Class<M> headerMetadataType) {
        super(headerMetadataType);
    }

    @Override
    protected final String findPropertyName(final M headerMetadata) {
        return headerMetadata.findPropertyName();
    }

    @Override
    protected final String[] findAllowableValues(final List<IBodyElement> tableElements, final M headerMetadata) {
        return headerMetadata.getValues();
    }
}
