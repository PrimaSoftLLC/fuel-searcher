package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwentyFifthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";

    public ExpectedTwentyFifthTableMetadataProvider() {
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
                        .propertyName("тип почвы")
                        .allowableValues(new String[]{"Легкие почвы", "Средние почвы", "Тяжелые почвы"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Беларус 1221", "New Holland TL 80"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"КПБ-260-2", "Grimme 1500 dr"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина междурядий")
                        .allowableValues(new String[]{"90", "70"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"До 15", "15-20", "20-25", "25-30", "30-35", "Свыше 35"})
                        .build()
        );
    }
}
