package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class TwentyThirdTablePropertyMetadataViewsProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentyThirdTablePropertyMetadataViewsProvider() {
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
                        "механизм",
                        new String[]{"BIG X 770", "BIG X 700", "NEW HOLLAND 600", "JOHN DEERE 8400", "JAGUAR 870", "JAGUAR 850", "КВК 800"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"3.8", "3", "4"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"5-7.5", "7.5-8.5", "8.5-9.5", "9.5-10.5", "10.5-11.5", "11.5-12.5", "12.5-13.5", "13.5-14.5", "14.5-15.5", "15.5-16.5", "16.5-17.5"}
                )
        );
    }
}
