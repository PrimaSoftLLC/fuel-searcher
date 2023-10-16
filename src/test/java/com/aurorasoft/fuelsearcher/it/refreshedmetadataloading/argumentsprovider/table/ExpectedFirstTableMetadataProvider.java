package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedFirstTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    public ExpectedFirstTableMetadataProvider() {
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
                        .propertyName("удельное сопротивление")
                        .allowableValues(new String[]{"Удельное сопротивление 48...53 кПа", "Удельное сопротивление 54...59 кПа", "Удельное сопротивление 60...65 кПа"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"FENDT 1050", "Кировец К-744 Р4", "Кировец К-744 Р3", "Fendt 936 Vario", "Беларус 3525", "Fendt 936", "Беларус 3522", "Кировец K744P2", "John Deere 8520", "К 744Р3"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Lemken Diamand 11", "ППО-8-40", "ППУ-13", "Kverneland RW 110", "Kverneland PG-100", "Vari Titan 10 7+3 L100", "ППШ-10-35", "Kuhn Challenger NSH-9", "ППО-9-45", "ППО-9-30/45"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("количество корпусов")
                        .allowableValues(new String[]{"9", "8", "13", "12", "10"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("глубина вспашки")
                        .allowableValues(new String[]{"18-20", "21-22", "23-25", "25-27"})
                        .build()
        );
    }
}
