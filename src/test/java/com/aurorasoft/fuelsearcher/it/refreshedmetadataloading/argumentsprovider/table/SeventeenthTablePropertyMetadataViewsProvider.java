package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class SeventeenthTablePropertyMetadataViewsProvider extends TableMetadataProvider {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";

    public SeventeenthTablePropertyMetadataViewsProvider() {
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
                        new String[]{"Беларус 1221", "Беларус 920.2", "Беларус 82"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"ГРЛ-9.6", "Claas Liner 1250", "Spider 600/6 ALP", "Krone KW 7.82/6x7", "Tonutti Millennium V16"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"9.6", "6.2", "6", "7.8"}
                )
        );
    }
}
