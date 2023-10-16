package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class TwentySecondTablePropertyMetadataViewsProvider extends TableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ЛЕНТ ЛЬНА";

    public TwentySecondTablePropertyMetadataViewsProvider() {
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
                        new String[]{"Беларус 80/82"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"ППЛ-1"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"1"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"до 9", "9-11", "11-13", "13-15", "15-17", "свыше 17"}
                )
        );
    }
}
