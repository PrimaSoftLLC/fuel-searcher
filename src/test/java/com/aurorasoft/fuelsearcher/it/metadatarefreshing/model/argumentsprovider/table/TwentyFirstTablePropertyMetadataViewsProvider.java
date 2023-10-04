package com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class TwentyFirstTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА";

    public TwentyFirstTablePropertyMetadataViewsProvider() {
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
                        "трактор",
                        new String[]{"Беларус 1221"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"KRONE CF Ultima 155 XC"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"5"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"до 5", "5-7.5", "7.5-8.5", "8.5-9.5", "9.5-10.5", "10.5-11.5", "11.5-12.5", "12.5-13.5", "13.5-14.5", "14.5-15.5", "свыше 15.5"}
                )
        );
    }
}
