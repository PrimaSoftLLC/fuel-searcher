package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedSeventeenthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";

    public ExpectedSeventeenthTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Беларус 1221", "Беларус 920.2", "Беларус 82"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"ГРЛ-9.6", "Claas Liner 1250", "Spider 600/6 ALP", "Krone KW 7.82/6x7", "Tonutti Millennium V16"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"9.6", "6.2", "6", "7.8"})
                        .build()
        );
    }
}
