package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedSixteenthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ";

    public ExpectedSixteenthTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Fendt-933", "John Deere-8430", "John Deere-8420", "Беларус 3022", "Беларус 2522", "Беларус 2022", "Беларус 1221", "Беларус 920.2"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Krone-B 1000 CV Collet", "Fella SM 991 TL-KCB + SM 310 FZ-KC", "КМР-9П", "Easy Cut B 1000CV", "Easy Cut B 870CV", "Easy Cut F 320CV", "KRONE EC-280"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"9.7", "9.1", "9", "9.28", "8.3", "3.16", "2.8"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"до 10", "10-15", "15-20", "20-25", "25-30", "30-35", "свыше 35"})
                        .build()
        );
    }
}
