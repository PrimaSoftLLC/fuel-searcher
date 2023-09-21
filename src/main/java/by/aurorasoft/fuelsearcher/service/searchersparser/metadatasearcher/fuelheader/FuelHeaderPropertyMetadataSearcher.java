package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.fuelheader;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.PropertyMetadataSearcher;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

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
    protected final Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final M headerMetadata) {
        final String[] allowableValues = headerMetadata.getValues();
        return stream(allowableValues);
    }

    @Override
    protected final boolean isAllowableValuesDuplicated() {
        return false;
    }
}
