package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.List;

public final class EighteenthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "СГРЕБАНИЕ СЕНА В ВАЛКИ";

    public EighteenthTableMetadataProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected List<PropertyMetadata> providePropertiesMetadata() {
        return List.of(
                PropertyMetadata.builder()
                        .propertyName("длина гона")
                        .allowableValues(new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус 920.2", "Беларус 80/82.1+"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Krone Swadro 807", "MILLENNIUM V18-7GW", "Claas Liner 1650 Twin"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"6.2", "10.5", "6.8"})
                        .build()
        );
    }
}
