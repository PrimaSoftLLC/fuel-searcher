package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class TwentyFifthTablePropertyMetadataViewsProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";

    public TwentyFifthTablePropertyMetadataViewsProvider() {
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
                        "тип почвы",
                        new String[]{"Легкие почвы", "Средние почвы", "Тяжелые почвы"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Беларус 1221", "New Holland TL 80"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"КПБ-260-2", "Grimme 1500 dr"}
                ),
                factory.create(
                        "ширина междурядий",
                        new String[]{"90", "70"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"До 15", "15-20", "20-25", "25-30", "30-35", "Свыше 35"}
                )
        );
    }
}
