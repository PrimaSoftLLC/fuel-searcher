package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedNinthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";

    public ExpectedNinthTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Норма высева семян 15 кг/га", "Норма высева семян 30 кг/га", "Норма высева семян 45 кг/га"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"John Deere 8400", "Беларус 1523", "John Deere 6140", "Беларус 1221", "Беларус 952.2", "Беларус 80/82"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Tempo V-18", "Matrix-18", "Tempo V-12", "Mater Macc Super", "Tehnic NC", "Ферабокс Футура Макси 12", "SP 12 R 45", "Meca V-4", "Mater Macc MS 4100", "SP 6 R 45"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"8.1", "5.4", "7.2", "2.7", "3.6"})
                        .build()
        );
    }
}
