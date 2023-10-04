package com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class FourthTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(final PropertyMetadataViewFactory factory) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "глубина обработки",
                        new String[]{"Глубина обработки 6...8 см", "Глубина обработки 8...10 см", "Глубина обработки 10...14 см", "Глубина обработки 14...20 см"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Clas Xerion 5000", "Кировец К-744Р4", "Fendt 936", "Беларус 3522", "Case in Magnum 340", "Fendt 930", "John Deere 8430", "Беларус 3022", "Кировец К744Р1", "John Deere 8420", "Fendt 927", "Беларус 2522", "Беларус 2022", "Case IN Puma 210", "Беларус 1522"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Köckerling Allrounder 1200", "КПМ-14", "КШП-10", "Köckerling Allrounder 900", "Horsch Tiger 8MT", "Kverneland STS 600", "АПМ-10", "КУ-10", "АПМ-8", "Köckerling Vector 800", "КФУ-7.8", "КФУ-7.3", "Köckerling Vector 620", "КГП-6.2", "Cuhn Cultimer L 6000", "АП-6-13", "АКПН-6", "Köckerling Vector 570", "Lemken Karat 9/500KA", "Vibro Master 3083SS", "КПМ-12", "Horsch Tiger 6MT", "КШП-8.5", "Köckerling Allrounder 750", "John Deere 714 (17 стоек)", "Horsch Terrano 6FX", "Kverneland STS 500", "Lemken Korund 8/900", "Köckerling Vector 460", "SYNCRO 6030T", "Horsch Tiger 5MT", "Horsch Tiger 4MT", "Horsch Terrano 4FX", "КГП-4.6", "КШУ-12", "КСО-9.6", "Vaderstad Aggressive 700"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"12", "14", "10", "9", "8", "6", "10.3", "7.8", "7.3", "6.2", "5.7", "5", "8.3", "8.5", "7.5", "6.5", "4.6", "4", "9.6", "7"}
                )
        );
    }
}
