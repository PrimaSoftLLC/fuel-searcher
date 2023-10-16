package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class TwentiethTablePropertyMetadataViewsProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА";

    public TwentiethTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> providePropertiesMetadata(final PropertyMetadataViewFactory factory) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Беларус 2022", "Fendt 515c", "Беларус 1221", "Беларус-920.2", "Беларус 82"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"CAMPRIMA CF 155 XC", "KRONE Comprima CV 150 XC", "Krone CAMPRIMA CF 155 XC", "Claas Variant 280", "Krone CAMPRIMA CF 125"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"5.7", "5.5"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"До 1", "1.1-1.5", "1.6-2", "2.1-2.5", "2.6-3", "3.1-3.5", "3.6-4", "4.1-4.5", "4.6-5", "5.1-5.5"}
                )
        );
    }
}
