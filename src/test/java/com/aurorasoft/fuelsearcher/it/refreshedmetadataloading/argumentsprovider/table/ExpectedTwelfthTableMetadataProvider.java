package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwelfthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    public ExpectedTwelfthTableMetadataProvider() {
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
                        .allowableValues(new String[]{"Berthoud Raptor 4200, 36 м", "Mecosan Technoma Lazer 4240, 24 м", "Amazone Pantera 4502, 24 м", "Mazzotti IBIS 3180 LP, 24 м"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("тип удобрения")
                        .allowableValues(new String[]{"Гранулированные удобрения"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("способ загрузки удобрений и расстояние транспортировки")
                        .allowableValues(new String[]{"Механизированный с загрузкой в конце гона", "Механизированный с подъездом до 500 м", "Механизированный с подъездом 501...1000 м", "Механизированный с подъездом 1001...2000 м", "Механизированный с подъездом 2001...3000 м"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("норма внесения")
                        .allowableValues(new String[]{"До 100", "100...150", "150...200", "200...250", "250...300", "300...350", "350...400", "400...450", "450...500", "500...550", "550...600"})
                        .build()
        );
    }
}
