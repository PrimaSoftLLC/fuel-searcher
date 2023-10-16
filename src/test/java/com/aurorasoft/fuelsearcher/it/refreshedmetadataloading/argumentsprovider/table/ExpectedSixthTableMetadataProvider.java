package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedSixthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ";

    public ExpectedSixthTableMetadataProvider() {
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
                        .propertyName("глубина обработки")
                        .allowableValues(new String[]{"Глубина обработки 6...8 см", "Глубина обработки 8...10 см", "Глубина обработки 10...14 см"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"Кировец К-744Р4", "Кировец К-744Р3", "Кировец К-744Р2", "Беларус 3522", "Fendt 933", "John Deere 8430", "Беларус 3022", "Fendt 930", "Fendt 927", "Беларус 2522", "Беларус 2022"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Доминанта Д-880", "БДК-8", "АДС-6", "БДК-6.4", "БДК-5.4", "Kuhn Optimer 7503", "БДК-7.5", "АД-600 \"Рубин\"", "Horsch Joker 8RT", "Horsch Joker 6HD", "Kuhn Discover XM48", "АКЧ-8", "АПД-7.5", "БДМ-6Х2П", "Amazone Caros 6001-2", "КЧД-6", "Kuhn Discover XL52", "ВТС-60 (ВТСН-60)", "АКЧ-6"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"8.8", "8", "6", "6.4", "5.4", "7.5", "5.6", "6.1"})
                        .build()
        );
    }
}
