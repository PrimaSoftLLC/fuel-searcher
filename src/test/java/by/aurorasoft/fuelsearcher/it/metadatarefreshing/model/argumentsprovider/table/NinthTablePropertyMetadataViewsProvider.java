package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class NinthTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";

    public NinthTablePropertyMetadataViewsProvider() {
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
                        "норма высева",
                        new String[]{"Норма высева семян 15 кг/га", "Норма высева семян 30 кг/га", "Норма высева семян 45 кг/га"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"John Deere 8400", "Беларус 1523", "John Deere 6140", "Беларус 1221", "Беларус 952.2", "Беларус 80/82"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Tempo V-18", "Matrix-18", "Tempo V-12", "Mater Macc Super", "Tehnic NC", "Ферабокс Футура Макси 12", "SP 12 R 45", "Meca V-4", "Mater Macc MS 4100", "SP 6 R 45"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"8.1", "5.4", "7.2", "2.7", "3.6"}
                )
        );
    }
}
