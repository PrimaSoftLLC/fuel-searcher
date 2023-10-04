package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;

import java.util.stream.Stream;

public final class SixteenthTableMetadataArgumentsProvider extends TableMetadataArgumentsProvider {
    private static final String TABLE_NAME = "КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ";

    public SixteenthTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataArguments> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    ) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Fendt-933", "John Deere-8430", "John Deere-8420", "Беларус 3022", "Беларус 2522", "Беларус 2022", "Беларус 1221", "Беларус 920.2"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Krone-B 1000 CV Collet", "Fella SM 991 TL-KCB + SM 310 FZ-KC", "КМР-9П", "Easy Cut B 1000CV", "Easy Cut B 870CV", "Easy Cut F 320CV", "KRONE EC-280"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"9.7", "9.1", "9", "9.28", "8.3", "3.16", "2.8"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"до 10", "10-15", "15-20", "20-25", "25-30", "30-35", "свыше 35"}
                )
        );
    }
}
