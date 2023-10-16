package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwentiethTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА";

    public ExpectedTwentiethTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Беларус 2022", "Fendt 515c", "Беларус 1221", "Беларус-920.2", "Беларус 82"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"CAMPRIMA CF 155 XC", "KRONE Comprima CV 150 XC", "Krone CAMPRIMA CF 155 XC", "Claas Variant 280", "Krone CAMPRIMA CF 125"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"5.7", "5.5"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"До 1", "1.1-1.5", "1.6-2", "2.1-2.5", "2.6-3", "3.1-3.5", "3.6-4", "4.1-4.5", "4.6-5", "5.1-5.5"})
                        .build()
        );
    }
}
