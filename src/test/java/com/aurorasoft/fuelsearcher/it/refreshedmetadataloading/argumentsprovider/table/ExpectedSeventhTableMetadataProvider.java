package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedSeventhTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: "
            + "ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА";

    public ExpectedSeventhTableMetadataProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Set<PropertyMetadata> providePropertiesMetadata() {
        return Set.of(
                PropertyMetadata.builder()
                        .propertyName("длина гона")
                        .allowableValues(new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("норма высева")
                        .allowableValues(new String[]{"Норма высева 120-180 кг/га", "Норма высева 180-240 кг/га", "Норма высева 240-280 кг/га"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Кировец К-744 Р3", "Беларус 3522", "Case Magnum 340", "Fendt 933", "Беларус 3022", "Fendt 927", "Беларус 2022", "Беларус 1221"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Horsch Serto 12 SC", "Kuhn Espro 8000R", "Köckerlinger UltimaCS 600", "Horsch Pronto 8 DS", "Amazone Cirius 6003-2C", "Horsch Pronto 6 DS", "Kuhn Espro 6000R", "Köckerlinger Vitu 600", "Kverneland Accord MSC 6000", "Rabe Mega Sed", "АКПД-6 Р", "Amazone Cirius 6001 Super", "АПП-6-02", "Amazone Avant 6001-2", "Lemken Solitair 12", "Horsch Sprinter 6 ST", "Amazone D660 Super", "Horsch Sprinter 4 ST"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"12", "8", "6", "4"})
                        .build()
        );
    }
}
