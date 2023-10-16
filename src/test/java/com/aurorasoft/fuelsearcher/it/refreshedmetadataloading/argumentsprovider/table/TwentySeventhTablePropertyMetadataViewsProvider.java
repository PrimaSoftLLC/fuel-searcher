package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import java.util.stream.Stream;

public final class TwentySeventhTablePropertyMetadataViewsProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ";

    public TwentySeventhTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> providePropertiesMetadata(final PropertyMetadataViewFactory factory) {
        return Stream.of(
                factory.create(
                        "группа дорог",
                        new String[]{"I", "II", "III"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"БЕЛАРУС-3522", "БЕЛАРУС-3022", "БЕЛАРУС-1221", "Беларус 920"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"ПСС-25", "ПСС-20", "ПСТБ-17", "BAASTRUP 18", "СТС-12", "ПС-30"}
                ),
                factory.create(
                        "класс груза",
                        new String[]{"Грузы I класса", "Грузы II класса", "Грузы III класса", "Грузы IV класса"}
                ),
                factory.create(
                        "расстояние транспортировки",
                        new String[]{"0.25-0.75", "0.76-1.25", "1.26-1.75", "1.76-2.25", "2.26-2.75", "2.76-3.25", "3.26-4", "4.1-5", "5.1-6", "6.1-7", "7.1-8", "8.1-9", "9.1-10", "10.1-12", "12.1-14", "14.1-16", "16.1-18", "18.1-21", "21.1-24", "24.1-28", "28.1-32", "32.1-36", "36.1-40", "40.1-45", "45.1-50"}
                )
        );
    }
}
