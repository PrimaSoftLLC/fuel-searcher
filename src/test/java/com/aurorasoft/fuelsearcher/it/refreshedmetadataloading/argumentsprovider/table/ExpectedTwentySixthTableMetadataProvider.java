package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwentySixthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public ExpectedTwentySixthTableMetadataProvider() {
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
                        .propertyName("комбайн")
                        .allowableValues(new String[]{"JAGUAR 970", "BIG X850", "BIG X770", "BIG X700", "JAGUAR 870", "JOHN DEERE 8400", "JOHN DEERE 8300", "JOHN DEERE 7300", "КВК 800"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"9", "10.5", "7.5", "6", "4.5"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"до 10", "10-15", "15-20", "20-25", "25-30", "30-35", "35-40", "40-45", "45-50", "50-55", "55-60", "60-65", "65-70"})
                        .build()
        );
    }
}
