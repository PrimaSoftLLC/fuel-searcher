package com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class TwentySixthTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentySixthTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(final PropertyMetadataViewFactory factory) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "комбайн",
                        new String[]{"JAGUAR 970", "BIG X850", "BIG X770", "BIG X700", "JAGUAR 870", "JOHN DEERE 8400", "JOHN DEERE 8300", "JOHN DEERE 7300", "КВК 800"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"9", "10.5", "7.5", "6", "4.5"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"до 10", "10-15", "15-20", "20-25", "25-30", "30-35", "35-40", "40-45", "45-50", "50-55", "55-60", "60-65", "65-70"}
                )
        );
    }
}
