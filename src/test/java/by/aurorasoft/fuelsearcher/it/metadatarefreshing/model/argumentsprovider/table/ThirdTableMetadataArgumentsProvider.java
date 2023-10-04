package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class ThirdTableMetadataArgumentsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    public ThirdTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    ) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "тип почвы",
                        new String[]{"Минеральные почвы", "Торфяные почвы"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Беларус-3522", "Беларус-3022"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"ПБН-6-50А"}
                ),
                factory.create(
                        "количество корпусов",
                        new String[]{"6"}
                ),
                factory.create(
                        "глубина вспашки",
                        new String[]{"21-22", "23-25", "25-27", "27-30", "30-35"}
                )
        );
    }
}
