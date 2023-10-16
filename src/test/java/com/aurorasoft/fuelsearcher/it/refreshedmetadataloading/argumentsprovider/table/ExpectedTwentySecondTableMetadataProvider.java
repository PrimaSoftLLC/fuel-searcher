package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwentySecondTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ЛЕНТ ЛЬНА";

    public ExpectedTwentySecondTableMetadataProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Set<PropertyMetadata> providePropertiesMetadata() {
        return Set.of(
                PropertyMetadata.builder()
                        .propertyName("длина гона")
                        .allowableValues(new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус 80/82"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"ППЛ-1"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"1"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"до 9", "9-11", "11-13", "13-15", "15-17", "свыше 17"})
                        .build()
        );
    }
}
