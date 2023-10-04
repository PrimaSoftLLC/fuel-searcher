package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class TenthTableMetadataArgumentsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";

    public TenthTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(
            final PropertyMetadataViewFactory factory
    ) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "норма высева",
                        new String[]{"Норма высева семян 3 кг/га", "Норма высева семян 5 кг/га", "Норма высева семян 6 кг/га", "Норма высева семян 8 кг/га"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Беларус 3522", "Беларус 3022", "Fendt 926", "Беларус 1523", "Беларус 1221", "Rabe Mega Sed"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Амкодор \"Veras\" 12000", "СЗП-12 \"Лидер\"", "Horsch Pronto 6 DS", "Rabe Mega Sed", "Amazone Avant 6001-2", "АППМ \"Берестье\"", "АПП-6П", "Horsch Focus 4 DS", "Lemken Sapfir 7/300DS", "Horsch Maestro DV"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"12", "6", "4", "3"}
                )
        );
    }
}
