package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class EighthTableMetadataArgumentsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА";

    public EighthTableMetadataArgumentsProvider() {
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
                        "норма высева",
                        new String[]{"Норма высева 15 кг/га", "Норма высева 30 кг/га", "Норма высева 45 кг/га"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Беларус 3522", "New Holland T8040", "Fendt 933", "John Deere 8310", "Беларус 3022", "John Deere 8420", "Беларус 2022", "Беларус 1523", "Беларус 1221"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Horsch Maestro 24 S", "Terrasem C 6", "Kinze 3600", "Tempo R 12", "Kuhn Maxima 2R", "Master Mass MS 8100 Super", "Ферабокс Футура Макси 12", "Kinze 3200", "Kverneland Ohtima Accjrd HD", "Kverneland Ohtima 8 HD", "Kinze 3000", "Maschio Gaspardio MTR 8 R 70", "Ферабокс Футура Макси 8", "Venza 8 PRO", "NC Tehnik Monjsem", "NC Tehnik Mojnjsem"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"16.8", "6", "11.2", "8.4", "5.6", "7.5", "9.5"}
                )
        );
    }
}
