package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedTwentyFourthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ";

    public ExpectedTwentyFourthTableMetadataProvider() {
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
                        .propertyName("комбайн")
                        .allowableValues(new String[]{"CLAAS LEXION-770 - 524 л.с.", "КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.", "CLAAS LEXION-760 - 461 л.с.", "КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.", "CLAAS LEXION-560 - 360 л.с.", "JOHN DEERE 9640WTS-770 - 524 л.с."})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("соотношение массы зерна к массе соломы")
                        .allowableValues(new String[]{"1:1", "1:1.5", "1:2"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("ширина захвата")
                        .allowableValues(new String[]{"10.5", "12", "9.2", "9", "7", "6.6", "6"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("урожайность")
                        .allowableValues(new String[]{"До 1.4", "1.4...1.8", "1.8...2.2", "2.2...2.6", "2.6...3", "3...3.4", "3.4...3.8", "3.8...4.1", "4.1...4.5", "4.5...4.9", "4.9...5.3", "5.3...5.6", "5.6...5.9", "5.9...6.3", "6.3...6.7", "6.7...7.1", "7.1...7.5", "Св. 7.5", "До 1.2", "1.2...1.5", "1.5...1.8", "1.8...2.1", "2.1...2.4", "2.4...2.7", "2.7...3", "3...3.3", "3.3...3.6", "3.6...3.9", "3.9...4.2", "4.2...4.5", "4.5...4.8", "4.8...5.1", "5.1...5.4", "5.4...5.7", "5.7...6", "Св. 6", "До 0.9", "0.9...1.2", "1.8...2", "2...2.2", "2.2...2.5", "2.5...2.7", "3.3...3.5", "3.5...3.7", "3.7...4", "4...4.2", "4.5...4.7", "4.7...5", "Св. 5"})
                        .build()
        );
    }
}
