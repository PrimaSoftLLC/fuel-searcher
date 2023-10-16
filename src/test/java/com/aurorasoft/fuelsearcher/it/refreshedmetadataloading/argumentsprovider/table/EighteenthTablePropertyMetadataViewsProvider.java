package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class EighteenthTablePropertyMetadataViewsProvider extends TableMetadataProvider {
    private static final String TABLE_NAME = "СГРЕБАНИЕ СЕНА В ВАЛКИ";

    public EighteenthTablePropertyMetadataViewsProvider() {
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
                        new String[]{"Беларус 920.2", "Беларус 80/82.1+"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Krone Swadro 807", "MILLENNIUM V18-7GW", "Claas Liner 1650 Twin"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"6.2", "10.5", "6.8"}
                )
        );
    }
}
