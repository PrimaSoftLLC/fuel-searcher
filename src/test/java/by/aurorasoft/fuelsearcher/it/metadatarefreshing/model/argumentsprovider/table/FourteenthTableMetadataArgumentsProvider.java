package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;

import java.util.stream.Stream;

public final class FourteenthTableMetadataArgumentsProvider extends TableMetadataArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public FourteenthTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataArguments> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    ) {
        return Stream.of(
                factory.create(
                        "норма внесения",
                        new String[]{"Менее 30", "30-50", "Более 50"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"FENDT 936", "CASE IN MAGNUM 340", "JOHN DEERE 6930"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"МЖТ-20", "МЖТ-16"}
                ),
                factory.create(
                        "группа дорог",
                        new String[]{"Первая группа дорог", "Вторая группа дорог", "Третья группа дорог"}
                ),
                factory.create(
                        "расстояние транспортировки",
                        new String[]{"0.25...0.75", "0.76...1.25", "1.26...1.75", "1.76...2.25", "2.26...2.75", "2.76...3.25", "3.26...4", "4.1...5", "5.1...6", "6.1...7", "7.1...8", "8.1...9", "9.1...10", "10.1...12", "12.1...14", "14.1...16", "16.1...18", "18.1...21", "21.1...24", "24.1...28", "28.1...32"}
                )
        );
    }
}
