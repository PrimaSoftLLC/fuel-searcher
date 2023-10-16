package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedSecondTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public ExpectedSecondTableMetadataProvider() {
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
                        .propertyName("удельное сопротивление")
                        .allowableValues(new String[]{"Удельное сопротивление 36...41 кПа", "Удельное сопротивление 42...47 кПа", "Удельное сопротивление 48...53 кПа", "Удельное сопротивление плуга 54...59 кПа", "Удельное сопротивление 60...65 кПа", "Удельное сопротивление 66...71 кПа"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"CASE II Steiger 550", "FENDT 100", "Кировец К-744 Р4", "Кировец К-744 Р3", "Massey Ferguson 8737S", "Fendt936 Vario", "Беларус 3525", "Fendt 936", "Беларус 3522", "Кировец K744 P2", "Case Magnum 340", "Jhon Deer 8530", "Беларус 3022", "Кировец К701", "John Deere 8520", "Fendt 927 Vario", "John Deere 8420", "Беларус 2522", "Кировец К-424", "Беларус 2022", "Беларус 1822", "Беларус 1523", "FENDT 1050"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"Lemken EuroTitan 10 8+3+1", "Lemken Diamand 11", "Lemken EuroTitan 10", "ППО-8-40К", "ПБН-6-50А", "ППУ-13", "Kverneland RW 110", "Lemkem Diamant 16", "Kverneland PG-100", "Lemken Diamant 11 7 L100", "Vari Titan 10 7+3 L100", "ППШ-10-35", "ППО-9-45", "ППО-8-40", "ППН-8-30/50", "ППО-9-45К", "ПОМ-6+1+1", "ПН-8-40", "ППН-7-30/50", "ПН-8-35У", "ППО-9-30/45", "KUHN Manager F10", "Amazone Cayron 200", "Lemken EuroDiamant 6", "ППН-7-30", "Lemken EurOpal-5", "ПЛН-5-35", "ПО-(4+1)-40", "ПКМ-5-40Р", "ПО 4-40", "ПОН-4-40", "ПЛН-4-35"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("количество корпусов")
                        .allowableValues(new String[]{"12", "9", "10", "8", "6", "13", "7", "5", "4"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("глубина вспашки")
                        .allowableValues(new String[]{"18-20", "21-22", "23-25", "25-27"})
                        .build()
        );
    }
}
