package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTenthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";

    public ExpectedTenthTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Норма высева семян 3 кг/га", "Норма высева семян 5 кг/га", "Норма высева семян 6 кг/га", "Норма высева семян 8 кг/га"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус 3522", "Беларус 3022", "Fendt 926", "Беларус 1523", "Беларус 1221", "Rabe Mega Sed"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Амкодор \"Veras\" 12000", "СЗП-12 \"Лидер\"", "Horsch Pronto 6 DS", "Rabe Mega Sed", "Amazone Avant 6001-2", "АППМ \"Берестье\"", "АПП-6П", "Horsch Focus 4 DS", "Lemken Sapfir 7/300DS", "Horsch Maestro DV"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"12", "6", "4", "3"})
                        .build()
        );
    }
}
