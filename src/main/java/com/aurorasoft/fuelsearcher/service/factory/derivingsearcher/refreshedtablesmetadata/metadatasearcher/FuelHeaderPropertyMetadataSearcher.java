package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Component
public final class FuelHeaderPropertyMetadataSearcher extends PropertyMetadataSearcher<FuelHeaderMetadata> {

    public FuelHeaderPropertyMetadataSearcher() {
        super(FuelHeaderMetadata.class);
    }

    @Override
    protected Stream<String> findAllowableValues(final List<IBodyElement> tableElements,
                                                 final FuelHeaderMetadata metadata) {
        final String[] allowableValues = metadata.findHeaderValues();
        return stream(allowableValues);
    }

    @Override
    protected boolean isAllowableValuesDuplicated() {
        return false;
    }
}
