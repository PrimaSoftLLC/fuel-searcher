package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;

import java.util.stream.Stream;

public final class FirstTableMetadataArgumentsProvider extends TableMetadataArgumentsProvider {

    public FirstTableMetadataArgumentsProvider(final String tableName) {
        super(tableName);
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
                        "удельное сопротивление",
                        new String[]{"Удельное сопротивление 48...53 кПа", "Удельное сопротивление 54...59 кПа", "Удельное сопротивление 60...65 кПа"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"FENDT 1050", "Кировец К-744 Р4", "Кировец К-744 Р3", "Fendt 936 Vario", "Беларус 3525", "Fendt 936", "Беларус 3522", "Кировец K744P2", "John Deere 8520", "К 744Р3"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Lemken Diamand 11", "ППО-8-40", "ППУ-13", "Kverneland RW 110", "Kverneland PG-100", "Vari Titan 10 7+3 L100", "ППШ-10-35", "Kuhn Challenger NSH-9", "ППО-9-45", "ППО-9-30/45"}
                ),
                factory.create(
                        "количество корпусов",
                        new String[]{"9", "8", "13", "12", "10"}
                ),
                factory.create(
                        "глубина вспашки",
                        new String[]{"18-20", "21-22", "23-25", "25-27"}
                )
        );
    }
}
