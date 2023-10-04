package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;

import java.util.stream.Stream;

public final class FifteenthTableMetadataArgumentsProvider extends TableMetadataArgumentsProvider {
    private static final String TABLE_NAME = "КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ";

    public FifteenthTableMetadataArgumentsProvider() {
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
                        new String[]{"Беларус 2522", "Беларус 1523", "Беларус 1221", "Беларус 82.1"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"KDT 941", "Диск 900", "Novocat Alfa Motion 351", "Novocat Alfa Motion 301", "KUHN GMD-310", "KRONE EC-280", "Novadisc 352", "Диск 340", "КДЛ-3.14", "KDT-300", "КДЛ-2.71", "Metal Fach Z 011/3", "Disc 300 S ALP", "Metal Fach Z 011/2"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"9.14", "8.7", "3.46", "3.04", "3.1", "2.7", "3.25", "3.14", "3", "2.71", "3.2", "2.8"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"до 10", "10-15", "15-20", "20-25", "25-30", "30-35", "свыше 35"}
                )
        );
    }
}
