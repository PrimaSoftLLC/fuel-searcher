package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedEleventhTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ";

    public ExpectedEleventhTableMetadataProvider() {
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
                        .propertyName("механизм")
                        .allowableValues(new String[]{"AMAZONE ZG B 8200 SUPER", "Amazone ZA M 3000", "УРМ 10", "REWO 8200", "SULKY XT 160", "RCW 10000", "Amazone ZA-M-1200", "RAUCH AXIS M 30.2К", "Amazone ZA-M-1001 с надставкой бункера 500 л", "Amazone ZA-M-900", "Tornado 1300", "SULKY XT 100 Polivrac", "SULKY DX 20 c"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"CASE IN Puma 210", "Беларус 1523", "Беларус 1221", "New Holland TD 5.110", "Беларус 82"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("тип удобрения")
                        .allowableValues(new String[]{"Гранулированные удобрения", "Кристаллические удобрения", "Пылевидные удобрения"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("способ загрузки удобрений и расстояние транспортировки")
                        .allowableValues(new String[]{"Механизированный с загрузкой в конце гона", "Механизированный с подъездом 0.5...1.5", "Механизированный с подъездом 1.51...3.5", "Механизированный с подъездом 3.51...5", "Механизированный с подъездом 5.1...7", "Механизированный с подъездом 7.1...9", "Механизированный с подъездом 9.1...11", "Механизированный с подъездом 11.1...13", "Механизированный с подъездом 13.1...15"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("норма внесения")
                        .allowableValues(new String[]{"до 2", "2.1...4", "4.1...6", "6.1...8", "8.1...10"})
                        .build()
        );
    }
}
