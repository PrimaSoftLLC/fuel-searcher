package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedThirdTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    public ExpectedThirdTableMetadataProvider() {
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
                        .propertyName("тип почвы")
                        .allowableValues(new String[]{"Минеральные почвы", "Торфяные почвы"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус-3522", "Беларус-3022"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"ПБН-6-50А"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("количество корпусов")
                        .allowableValues(new String[]{"6"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("глубина вспашки")
                        .allowableValues(new String[]{"21-22", "23-25", "25-27", "27-30", "30-35"})
                        .build()
        );
    }
}
