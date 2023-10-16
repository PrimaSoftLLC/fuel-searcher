package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.List;

public final class EighthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА";

    public EighthTableMetadataProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected List<PropertyMetadata> providePropertiesMetadata() {
        return List.of(
                PropertyMetadata.builder()
                        .propertyName("длина гона")
                        .allowableValues(new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("норма высева")
                        .allowableValues(new String[]{"Норма высева 15 кг/га", "Норма высева 30 кг/га", "Норма высева 45 кг/га"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус 3522", "New Holland T8040", "Fendt 933", "John Deere 8310", "Беларус 3022", "John Deere 8420", "Беларус 2022", "Беларус 1523", "Беларус 1221"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Horsch Maestro 24 S", "Terrasem C 6", "Kinze 3600", "Tempo R 12", "Kuhn Maxima 2R", "Master Mass MS 8100 Super", "Ферабокс Футура Макси 12", "Kinze 3200", "Kverneland Ohtima Accjrd HD", "Kverneland Ohtima 8 HD", "Kinze 3000", "Maschio Gaspardio MTR 8 R 70", "Ферабокс Футура Макси 8", "Venza 8 PRO", "NC Tehnik Monjsem", "NC Tehnik Mojnjsem"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"16.8", "6", "11.2", "8.4", "5.6", "7.5", "9.5"})
                        .build()
        );
    }
}
